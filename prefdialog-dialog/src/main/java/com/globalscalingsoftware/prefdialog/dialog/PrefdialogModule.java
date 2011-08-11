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
package com.globalscalingsoftware.prefdialog.dialog;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;

import com.globalscalingsoftware.prefdialog.PreferenceDialogHandler;
import com.globalscalingsoftware.prefdialog.PreferenceDialogHandlerFactory;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.dialog.CreatePreferencePanelHandlersWorker.CreatePreferencePanelHandlersWorkerFactory;
import com.globalscalingsoftware.prefdialog.dialog.PreferenceDialog.PreferenceDialogFactory;
import com.globalscalingsoftware.prefdialog.dialog.PreferencePanelsCollection.PreferencePanelsCollectionFactory;
import com.globalscalingsoftware.prefdialog.dialog.PreferencePanelsHandler.PreferencePanelsHandlerFactory;
import com.globalscalingsoftware.prefdialog.panel.PanelModule;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;

/**
 * Binds the default dependencies for the preference dialog.
 * 
 * We can use the module to create a new instance of
 * {@link PreferenceDialogHandler} with the help of an {@link Injector}. As in
 * the example:
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
public class PrefdialogModule extends PanelModule {

	@Override
	protected void configure() {
		super.configure();
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<PreferenceDialogHandler>() {
				}, PreferenceDialogHandlerImpl.class).build(
				PreferenceDialogHandlerFactory.class));
		install(new FactoryModuleBuilder().implement(PreferenceDialog.class,
				PreferenceDialog.class).build(PreferenceDialogFactory.class));
		install(new FactoryModuleBuilder().implement(
				PreferencePanelsCollection.class,
				PreferencePanelsCollection.class).build(
				PreferencePanelsCollectionFactory.class));
		install(new FactoryModuleBuilder().implement(
				CreatePreferencePanelHandlersWorker.class,
				CreatePreferencePanelHandlersWorker.class).build(
				CreatePreferencePanelHandlersWorkerFactory.class));
		install(new FactoryModuleBuilder().implement(
				PreferencePanelsHandler.class, PreferencePanelsHandler.class)
				.build(PreferencePanelsHandlerFactory.class));

	}

	@Provides
	@Named("childAnnotations")
	Collection<Class<? extends Annotation>> bindAnnotations() {
		Collection<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
		annotations.add(Child.class);
		return annotations;
	}
}
