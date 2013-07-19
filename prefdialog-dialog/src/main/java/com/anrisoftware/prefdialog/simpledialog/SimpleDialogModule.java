/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-csvimportdialog.
 * 
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs simple dialog factory and dependent modules.
 * 
 * @see SimpleDialog
 * @see SimpleDialogFactory
 * @see SimplePropertiesDialog
 * @see SimplePropertiesDialogFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class SimpleDialogModule extends AbstractModule {

	private static Injector injector;

	/**
	 * Returns the simple dialog factory.
	 * 
	 * @param parent
	 *            the parent Guice {@link Injector} for the needed dependent
	 *            modules.
	 * 
	 * @return the {@link SimpleDialogFactory}.
	 */
	public static SimpleDialogFactory getSimpleDialogFactory(Injector parent) {
		injector = getInjector(parent);
		return injector.getInstance(SimpleDialogFactory.class);
	}

	/**
	 * Returns the simple properties dialog factory.
	 * 
	 * @param parent
	 *            the parent Guice {@link Injector} for the needed dependent
	 *            modules.
	 * 
	 * @return the {@link SimplePropertiesDialogFactory}.
	 */
	public static SimplePropertiesDialogFactory getSimplePropertiesDialogFactory(
			Injector parent) {
		injector = getInjector(parent);
		return injector.getInstance(SimplePropertiesDialogFactory.class);
	}

	private static Injector getInjector(Injector parent) {
		if (injector == null) {
			synchronized (SimpleDialogModule.class) {
				injector = parent.createChildInjector(new SimpleDialogModule());
			}
		}
		return injector;
	}

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(SimpleDialog.class,
				SimpleDialog.class).build(SimpleDialogFactory.class));
		install(new FactoryModuleBuilder().implement(
				SimplePropertiesDialog.class, SimplePropertiesDialog.class)
				.build(SimplePropertiesDialogFactory.class));
	}

}
