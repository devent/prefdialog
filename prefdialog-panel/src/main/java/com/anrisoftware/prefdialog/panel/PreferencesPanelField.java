/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.util.ServiceLoader;

import javax.inject.Inject;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationBean;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationDiscovery;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationDiscoveryFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationFilter;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationSetFilterFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanField;
import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.annotations.FieldAnnotation;
import com.anrisoftware.prefdialog.core.AbstractFieldComponent;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

/**
 * Panel that put the child field and its fields in one panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class PreferencesPanelField extends AbstractFieldComponent<JPanel> {

    /**
     * Suffix for the preference panel that contains the input fields. The name
     * of the preference panel will be {@code <name>-panel}, {@code <name>} is
     * the name of the field.
     */
    public static final String PANEL_SUFFIX = "panel";

    private static final Class<Child> CHILD_FIELD_ANNOTATION = Child.class;

    private final PreferencesPanelFieldLogger log;

    private transient ServiceLoader<FieldService> loader;

    private transient AnnotationDiscoveryFactory discoveryFactory;

    private transient AnnotationFilter filter;

    private transient BeanField beanField;

    private final TableLayout layout;

    private final JScrollPane scrollPane;

    private FieldComponent<?> childField;

    /**
     * @see PreferencesPanelFieldFactory#create(Object, String)
     */
    @Inject
    PreferencesPanelField(PreferencesPanelFieldLogger logger,
            @Assisted Object parentObject, @Assisted String fieldName) {
        super(new JPanel(), parentObject, fieldName);
        this.log = logger;
        this.layout = createLayout();
        this.scrollPane = new JScrollPane(getComponent());
        getComponent().setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        setupLayout();
        setupScrollPane();
    }

    @Inject
    void setFieldsServiceLoader(ServiceLoader<FieldService> loader,
            AnnotationDiscoveryFactory discoveryFactory,
            AnnotationSetFilterFactory filterFactory, BeanField beanField) {
        this.loader = loader;
        this.discoveryFactory = discoveryFactory;
        this.filter = filterFactory
                .create(com.anrisoftware.prefdialog.annotations.FieldComponent.class);
        this.beanField = beanField;
    }

    /**
     * Creates the preferences panel field.
     *
     * @param injector
     *            the parent {@link Injector}.
     *
     * @return this {@link PreferencesPanelField}.
     *
     * @since 3.6
     */
    public PreferencesPanelField createPanel(Injector injector) {
        this.childField = findService(CHILD_FIELD_ANNOTATION).getFactory(
                injector).create(getParentObject(), getFieldName());
        addField(childField);
        discoverFields(injector, getValue(), childField);
        return this;
    }

    @Override
    public void addField(FieldComponent<?> field) {
        super.addField(field);
        int rows = layout.getNumRow();
        layout.insertRow(rows, TableLayout.PREFERRED);
        layout.invalidateLayout(getComponent());
        getComponent().add(field.getAWTComponent(), format("%d,%d", 0, rows));
    }

    @Override
    public Component getAWTComponent() {
        return scrollPane;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        getComponent().setName(format("%s-%s", name, PANEL_SUFFIX));
    }

    private void setupScrollPane() {
        scrollPane.setBorder(null);
    }

    private TableLayout createLayout() {
        double[] col = { TableLayout.FILL };
        double[] row = {};
        return new TableLayout(col, row);
    }

    private void setupLayout() {
        getComponent().setLayout(layout);
    }

    private void discoverFields(Injector injector, Object object,
            FieldComponent<? extends Component> parentField) {
        AnnotationDiscovery discovery = createAnnotationDiscovery(object);
        for (AnnotationBean bean : discovery.call()) {
            FieldService service = findAnnotationService(bean);
            String fieldName = beanField.toFieldName(bean.getMember());
            FieldComponent<? extends Component> field = service.getFactory(
                    injector).create(object, fieldName);
            parentField.addField(field);
            if (service.getInfo().getAnnotationType()
                    .equals(CHILD_FIELD_ANNOTATION)) {
                discoverFields(injector, bean.getValue(), field);
            }
        }
    }

    private AnnotationDiscovery createAnnotationDiscovery(Object parentObject) {
        return discoveryFactory.create(parentObject, filter);
    }

    private FieldService findAnnotationService(AnnotationBean bean) {
        Annotation fieldAnnotation = findFieldAnnotation(bean);
        return findService(fieldAnnotation.annotationType());
    }

    private FieldService findService(Class<? extends Annotation> type) {
        for (FieldService service : loader) {
            if (service.getInfo().getAnnotationType().equals(type)) {
                return service;
            }
        }
        throw log.noFieldServiceFound(this, type);
    }

    private Annotation findFieldAnnotation(AnnotationBean bean) {
        for (Annotation a : bean.getMember().getAnnotations()) {
            if (a.annotationType().isAnnotationPresent(FieldAnnotation.class)) {
                return a;
            }
        }
        throw log.noFieldAnnotationFound(this, bean);
    }
}
