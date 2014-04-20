/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.tabspanel;

import java.awt.Component;
import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.Collection;

import javax.inject.Inject;
import javax.swing.Icon;
import javax.swing.JTabbedPane;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationBean;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationDiscovery;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationDiscoveryFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationFilter;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationSetFilterFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanField;
import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.core.AbstractFieldComponent;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.anrisoftware.prefdialog.panel.PreferencesPanelField;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

/**
 * Panel that collects input fields from annotated bean. Each child is put in a
 * tab in the tabbed pane.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class TabsPreferencesPanelField extends
		AbstractFieldComponent<JTabbedPane> {

	private static final String RENDERER_ATTRIBUTE = "renderer";

	private static final Class<? extends Annotation> ANNOTATION_CLASS = TabsPreferencesPanel.class;

	private final TabsPreferencesPanelFieldLogger log;

	private TabsRenderer renderer;

	private transient Object selectedField;

	private transient ChangeListener changeListener;

	private transient AnnotationDiscoveryFactory discoveryFactory;

	private transient AnnotationSetFilterFactory filterFactory;

	private transient AnnotationClassFactory annotationClassFactory;

	private transient BeanField beanField;

	private FieldService preferencesPanelService;

	/**
	 * @see TabsPreferencesPanelFieldFactory#create(Object, String)
	 */
	@Inject
	TabsPreferencesPanelField(TabsPreferencesPanelFieldLogger logger,
			@Assisted Object parentObject, @Assisted String fieldName) {
		super(new JTabbedPane(), parentObject, fieldName);
		this.log = logger;
		this.changeListener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				SingleSelectionModel model;
				model = (SingleSelectionModel) e.getSource();
				updateValue(model.getSelectedIndex());
			}
		};
		setupTabPane();
	}

	private void setupTabPane() {
		getComponent().getModel().addChangeListener(changeListener);
	}

	private void updateValue(int index) {
		FieldComponent<?> field = getField(index);
		updateValue(field);
	}

	private void updateValue(FieldComponent<?> field) {
		try {
			setValue(field.getValue());
		} catch (PropertyVetoException e) {
			log.valueVetoed(this, 0);
		}
	}

	@Inject
	void setupTabsPreferencesPanelField(FieldService preferencesPanelService,
			AnnotationDiscoveryFactory discoveryFactory,
			AnnotationSetFilterFactory filterFactory,
			AnnotationClassFactory annotationClassFactory, BeanField beanField) {
		this.preferencesPanelService = preferencesPanelService;
		this.discoveryFactory = discoveryFactory;
		this.filterFactory = filterFactory;
		this.annotationClassFactory = annotationClassFactory;
		this.beanField = beanField;
		setupRenderer();
	}

	private void setupRenderer() {
		TabsRenderer renderer = (TabsRenderer) annotationClassFactory
				.create(getParentObject(), ANNOTATION_CLASS,
						getAccessibleObject()).forAttribute(RENDERER_ATTRIBUTE)
				.build();
		setRenderer(renderer);
	}

	public void createPanel(Injector injector) {
		discoverChildren(injector);
	}

	private void discoverChildren(Injector injector) {
		AnnotationFilter filter = filterFactory.create(Child.class);
		Collection<AnnotationBean> annotations = createChildDiscovery(
				discoveryFactory, filter).call();
		FieldComponent<? extends Component> field;
		for (AnnotationBean bean : annotations) {
			field = loadField(bean, injector);
			addField(field);
		}
	}

	private FieldComponent<? extends Component> loadField(AnnotationBean bean,
			Injector injector) {
		AccessibleObject field = bean.getMember();
		String fieldName = beanField.toFieldName(field);
		Object object = getParentObject();
		PreferencesPanelField panelField = (PreferencesPanelField) preferencesPanelService
				.getFactory(injector).create(object, fieldName);
		panelField.createPanel(injector);
		return panelField;
	}

	private AnnotationDiscovery createChildDiscovery(
			AnnotationDiscoveryFactory discoveryFactory,
			AnnotationFilter childFilter) {
		return discoveryFactory.create(getParentObject(), childFilter);
	}

	/**
	 * Sets the specified renderer for the tabs group.
	 * 
	 * @param renderer
	 *            the {@link TabsRenderer}.
	 * 
	 * @throws NullPointerException
	 *             if the specified model is {@code null}.
	 */
	public void setRenderer(TabsRenderer renderer) {
		log.checkRenderer(this, renderer);
		this.renderer = renderer;
		if (renderer instanceof DefaultTabsRenderer) {
			setupDefaultRenderer((DefaultTabsRenderer) renderer);

		}
		log.rendererSet(this, renderer);
	}

	private void setupDefaultRenderer(DefaultTabsRenderer renderer) {
		renderer.setImages(getImages());
		renderer.setTexts(getTexts());
	}

	/**
	 * Returns the renderer for the tabs group.
	 * 
	 * @return the {@link TabsRenderer}.
	 */
	public TabsRenderer getRenderer() {
		return renderer;
	}

	@Override
	public void setComponent(JTabbedPane component) {
		removeOldTabPane();
		super.setComponent(component);
		setupTabPane();
	}

	private void removeOldTabPane() {
		JTabbedPane old = getComponent();
		if (old != null) {
			old.getModel().removeChangeListener(changeListener);
		}
	}

	@Override
	public void addField(FieldComponent<?> field) {
		super.addField(field);
		if (selectedField == null) {
			selectedField = field;
			updateValue(field);
		}
		JTabbedPane pane = getComponent();
		int index = pane.getTabCount();
		String title = title(field, index);
		String tip = renderer.getTip(index);
		Icon icon = renderer.getIcon(index);
		pane.addTab(title, icon, field.getAWTComponent(), tip);
	}

	private String title(FieldComponent<?> field, int index) {
		String title = renderer.getTitle(index);
		return title == null ? field.getTitle() : title;
	}

}
