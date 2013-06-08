/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.util.ServiceLoader;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationBean;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationDiscovery;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationDiscoveryFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationFilter;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationSetFilterFactory;
import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.annotations.FieldAnnotation;
import com.anrisoftware.prefdialog.core.AbstractFieldComponent;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.assistedinject.Assisted;

/**
 * Panel that collects input fields from annotated bean.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class PreferencesPanel extends AbstractFieldComponent<JPanel> {

	private final PreferencesPanelLogger log;

	private transient ServiceLoader<FieldService> loader;

	private transient AnnotationDiscoveryFactory discoveryFactory;

	private AnnotationFilter filter;

	private final TableLayout layout;

	private final JScrollPane scrollPane;

	/**
	 * @see PreferencesPanelFactory#create(Object, String)
	 */
	@Inject
	PreferencesPanel(PreferencesPanelLogger logger,
			@Assisted Object parentObject, @Assisted String fieldName) {
		super(new JPanel(), parentObject, fieldName);
		this.log = logger;
		this.layout = createLayout();
		this.scrollPane = new JScrollPane(getComponent());
		setupLayout();
		setupScrollPane();
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

	@Inject
	void setFieldsServiceLoader(ServiceLoader<FieldService> loader,
			AnnotationDiscoveryFactory discoveryFactory,
			AnnotationSetFilterFactory filterFactory) {
		this.loader = loader;
		this.discoveryFactory = discoveryFactory;
		this.filter = filterFactory
				.create(com.anrisoftware.prefdialog.annotations.FieldComponent.class);
		discoverFields(this, getParentObject());
	}

	private void discoverFields(
			FieldComponent<? extends Component> parentField, Object parentObject) {
		AnnotationDiscovery discovery = createAnnotationDiscovery(parentObject);
		for (AnnotationBean bean : discovery.call()) {
			FieldService service = findService(bean);
			String fieldName = bean.getField().getName();
			FieldComponent<? extends Component> field = service.getFactory()
					.create(parentObject, fieldName);
			parentField.addField(field);
			if (service.getInfo().getAnnotationType().equals(Child.class)) {
				discoverFields(field, bean.getValue());
			}
		}
	}

	private AnnotationDiscovery createAnnotationDiscovery(Object parentObject) {
		return discoveryFactory.create(parentObject, filter);
	}

	private FieldService findService(AnnotationBean bean) {
		Annotation fieldAnnotation = findFieldAnnotation(bean);
		for (FieldService service : loader) {
			if (service.getInfo().getAnnotationType()
					.equals(fieldAnnotation.annotationType())) {
				return service;
			}
		}
		throw log.noFieldServiceFound(this, bean);
	}

	private Annotation findFieldAnnotation(AnnotationBean bean) {
		for (Annotation a : bean.getMember().getAnnotations()) {
			if (a.annotationType().isAnnotationPresent(FieldAnnotation.class)) {
				return a;
			}
		}
		throw log.noFieldAnnotationFound(this, bean);
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
}
