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
package com.anrisoftware.prefdialog.tabspanel;

import java.awt.Component;
import java.beans.PropertyVetoException;
import java.util.ServiceLoader;

import javax.inject.Inject;
import javax.swing.JTabbedPane;
import javax.swing.SingleSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationBean;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationDiscovery;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationDiscoveryFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationFilter;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationSetFilterFactory;
import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.core.AbstractFieldComponent;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.anrisoftware.prefdialog.panel.PreferencesPanel;
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

	private static final Class<PreferencesPanel> PREFERENCE_PANEL_ANNOTATION = PreferencesPanel.class;

	private final TabsPreferencesPanelFieldLogger log;

	private transient ServiceLoader<FieldService> loader;

	private transient AnnotationFilter childFilter;

	private transient AnnotationDiscoveryFactory discoveryFactory;

	private transient Object selectedField;

	private transient ChangeListener changeListener;

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

	@Inject
	void setFieldsServiceLoader(ServiceLoader<FieldService> loader,
			AnnotationDiscoveryFactory discoveryFactory,
			AnnotationSetFilterFactory filterFactory) {
		this.loader = loader;
		this.discoveryFactory = discoveryFactory;
		this.childFilter = filterFactory.create(Child.class);
		discoverChildren();
	}

	private void discoverChildren() {
		FieldService service = getPreferencePanelService();
		for (AnnotationBean bean : createChildDiscovery().call()) {
			String fieldName = bean.getField().getName();
			FieldComponent<? extends Component> field = service.getFactory()
					.create(getParentObject(), fieldName);
			addField(field);
		}
	}

	private AnnotationDiscovery createChildDiscovery() {
		return discoveryFactory.create(getParentObject(), childFilter);
	}

	private FieldService getPreferencePanelService() {
		for (FieldService service : loader) {
			if (isPreferencePanelService(service)) {
				return service;
			}
		}
		throw log.noPreferencePanelService(this, PREFERENCE_PANEL_ANNOTATION);
	}

	private boolean isPreferencePanelService(FieldService service) {
		return service.getInfo().getAnnotationType()
				.equals(PREFERENCE_PANEL_ANNOTATION);
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
		getComponent().addTab(field.getTitle(), field.getAWTComponent());
	}

	private void updateValue(FieldComponent<?> field) {
		try {
			setValue(field.getValue());
		} catch (PropertyVetoException e) {
			log.valueVetoed(this, 0);
		}
	}
}
