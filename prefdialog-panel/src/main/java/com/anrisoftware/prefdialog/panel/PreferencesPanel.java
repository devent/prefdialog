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

import java.util.ServiceLoader;

import javax.inject.Inject;
import javax.swing.JPanel;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationBean;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationDiscovery;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationDiscoveryFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationFilter;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationSetFilterFactory;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.core.AbstractFieldComponent;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

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

	private transient AnnotationSetFilterFactory filterFactory;

	/**
	 * @see PreferencesPanelFactory#create(JPanel, Object, String)
	 */
	@AssistedInject
	PreferencesPanel(PreferencesPanelLogger logger, @Assisted JPanel container,
			@Assisted Object parentObject, @Assisted String fieldName) {
		super(container, parentObject, fieldName);
		this.log = logger;
	}

	@Inject
	void setFieldsServiceLoader(ServiceLoader<FieldService> loader,
			AnnotationDiscoveryFactory discoveryFactory,
			AnnotationSetFilterFactory filterFactory) {
		this.loader = loader;
		this.discoveryFactory = discoveryFactory;
		this.filterFactory = filterFactory;
		discoverFields();
	}

	private void discoverFields() {
		AnnotationFilter filter = filterFactory.create(FieldComponent.class);
		AnnotationDiscovery discovery = discoveryFactory.create(
				getParentObject(), filter);
		for (AnnotationBean bean : discovery.call()) {
			System.out.println(bean);// TODO println
		}
	}

}
