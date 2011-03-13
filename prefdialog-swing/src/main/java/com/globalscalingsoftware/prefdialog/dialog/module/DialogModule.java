/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.dialog.module;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;

import com.globalscalingsoftware.prefdialog.PreferenceDialogController;
import com.globalscalingsoftware.prefdialog.PreferenceDialogControllerFactory;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.dialog.internal.PreferenceDialog;
import com.globalscalingsoftware.prefdialog.dialog.internal.PreferenceDialogControllerImpl;
import com.globalscalingsoftware.prefdialog.dialog.internal.PreferencePanelsCollection;
import com.globalscalingsoftware.prefdialog.dialog.internal.PreferenceDialog.PreferenceDialogFactory;
import com.globalscalingsoftware.prefdialog.dialog.internal.PreferencePanelsCollection.PreferencePanelsCollectionFactory;
import com.globalscalingsoftware.prefdialog.panel.module.PanelModule;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryProvider;
import com.google.inject.name.Named;

/**
 * Binds the default dependencies for the preference dialog.
 * 
 * We can use the module to create a new instance of
 * {@link PreferenceDialogController} with the help of an {@link Injector}. As
 * in the example:
 * 
 * <pre>
 * injector = Guice.createInjector(new PreferenceDialogModule());
 * factory = injector.getInstance(PreferenceDialogFactory.class);
 * controller = factory.create(owner, preferences);
 * controller.openDialog();
 * if (controller.getOption() == OK) {
 *     compute preferences
 * }
 * </pre>
 */
public class DialogModule extends AbstractModule {

	@Override
	protected void configure() {
		binder().install(new PanelModule());
		bindPreferenceDialog();
	}

	private void bindPreferenceDialog() {
		bind(PreferenceDialogControllerFactory.class).toProvider(
				FactoryProvider.newFactory(
						PreferenceDialogControllerFactory.class,
						PreferenceDialogControllerImpl.class));
		bind(PreferenceDialogFactory.class).toProvider(
				FactoryProvider.newFactory(PreferenceDialogFactory.class,
						PreferenceDialog.class));
		bind(PreferencePanelsCollectionFactory.class).toProvider(
				FactoryProvider.newFactory(
						PreferencePanelsCollectionFactory.class,
						PreferencePanelsCollection.class));
	}

	@Provides
	@Named("childAnnotations")
	Collection<Class<? extends Annotation>> bindAnnotations() {
		Collection<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Child.class);
		return annotations;
	}
}
