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
package com.anrisoftware.prefdialog.verticalpanel;

import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.awt.BorderLayout.CENTER;
import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.PropertyVetoException;
import java.lang.reflect.AccessibleObject;
import java.util.ServiceLoader;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
import com.anrisoftware.prefdialog.panel.PreferencesPanel;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Vertical preferences panel. Each child is layout from top to bottom in one
 * panel.
 * 
 * @see VerticalPreferencesPanel
 * @see PreferencesPanel
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class VerticalPreferencesPanelField extends
		AbstractFieldComponent<JPanel> {

	/**
	 * Suffix for the children panel that contains the children preference
	 * panel. The name of the panel will be {@code <name>-childrenPanel},
	 * {@code <name>} is the name of the field.
	 */
	public static final String CHILDREN_PANEL_SUFFIX = "childrenPanel";

	private static final Class<PreferencesPanel> PREFERENCE_PANEL_ANNOTATION = PreferencesPanel.class;

	private final VerticalPreferencesPanelFieldLogger log;

	private final TableLayout layout;

	private final JScrollPane scrollPane;

	private final JPanel childenPanel;

	private transient BeanField beanField;

	/**
	 * @see VerticalPreferencesPanelFieldFactory#create(Object, String)
	 */
	@AssistedInject
	VerticalPreferencesPanelField(VerticalPreferencesPanelFieldLogger logger,
			@Assisted Object parentObject, @Assisted String fieldName) {
		super(new JPanel(), parentObject, fieldName);
		this.log = logger;
		this.layout = createLayout();
		this.childenPanel = new JPanel(layout);
		this.scrollPane = new JScrollPane();
		setupPanel();
		setupScrollPane();
	}

	private TableLayout createLayout() {
		double[] col = { FILL };
		double[] row = { FILL };
		TableLayout layout = new TableLayout(col, row);
		layout.setVGap(10);
		return layout;
	}

	private void setupPanel() {
		JPanel panel = getComponent();
		panel.removeAll();
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, CENTER);
	}

	private void setupScrollPane() {
		scrollPane.setViewportView(childenPanel);
	}

	@Inject
	void setFieldsServiceLoader(ServiceLoader<FieldService> loader,
			AnnotationDiscoveryFactory discoveryFactory,
			AnnotationSetFilterFactory filterFactory, BeanField beanField) {
		this.beanField = beanField;
		discoverChildren(loader, discoveryFactory, filterFactory);
	}

	private void discoverChildren(ServiceLoader<FieldService> loader,
			AnnotationDiscoveryFactory discoveryFactory,
			AnnotationSetFilterFactory filterFactory) {
		AnnotationFilter filter = filterFactory.create(Child.class);
		FieldService service = getPreferencePanelService(loader);
		for (AnnotationBean bean : createChildDiscovery(discoveryFactory,
				filter).call()) {
			FieldComponent<? extends Component> field = loadField(service, bean);
			addField(field);
		}
	}

	private FieldComponent<? extends Component> loadField(FieldService service,
			AnnotationBean bean) {
		AccessibleObject field = bean.getMember();
		String fieldName = beanField.toFieldName(field);
		return service.getFactory().create(getParentObject(), fieldName);
	}

	private AnnotationDiscovery createChildDiscovery(
			AnnotationDiscoveryFactory discoveryFactory,
			AnnotationFilter childFilter) {
		return discoveryFactory.create(getParentObject(), childFilter);
	}

	private FieldService getPreferencePanelService(
			ServiceLoader<FieldService> loader) {
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
	public void setComponent(JPanel component) {
		super.setComponent(component);
		setupPanel();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		childenPanel.setName(format("%s-%s", name, CHILDREN_PANEL_SUFFIX));
	}

	@Override
	public void addField(FieldComponent<?> field) {
		setupFirstField(field);
		super.addField(field);
		int rowIndex = layout.getNumRow() - 1;
		insertRow(rowIndex);
		childenPanel.add(field.getAWTComponent(), format("%d,%d", 0, rowIndex));
		layout.layoutContainer(childenPanel);
		childenPanel.repaint();
	}

	private void setupFirstField(FieldComponent<?> field) {
		if (getFieldsCount() > 0) {
			return;
		}
		try {
			setValue(field.getValue());
		} catch (PropertyVetoException e) {
			log.propertyVeto(this, e);
		}
	}

	private void insertRow(int rowIndex) {
		layout.insertRow(rowIndex, PREFERRED);
	}

	/**
	 * Returns the scroll pane of the children.
	 * 
	 * @return the {@link JScrollPane}.
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * Returns the panel of the children.
	 * 
	 * @return the {@link JPanel}.
	 */
	public JPanel getChildenPanel() {
		return childenPanel;
	}
}
