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
package com.globalscalingsoftware.prefdialog.module;

import com.globalscalingsoftware.prefdialog.PreferenceDialogController;
import com.globalscalingsoftware.prefdialog.PreferenceDialogFactory;
import com.globalscalingsoftware.prefdialog.internal.dialog.PreferenceDialogControllerImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryProvider;

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
public class PreferenceDialogModule extends AbstractModule {

	@Override
	protected void configure() {
		bindPreferenceDialog();
	}

	private void bindPreferenceDialog() {
		bind(PreferenceDialogFactory.class).toProvider(
				FactoryProvider.newFactory(PreferenceDialogFactory.class,
						PreferenceDialogControllerImpl.class));
	}

}
