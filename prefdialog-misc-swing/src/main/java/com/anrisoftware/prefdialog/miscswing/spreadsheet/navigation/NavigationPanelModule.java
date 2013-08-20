/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-misc-swing.
 * 
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation;

import static com.google.inject.Guice.createInjector;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs spreadsheet pane factory.
 * 
 * @see NavigationPanel
 * @see NavigationPanelFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class NavigationPanelModule extends AbstractModule {

	/**
	 * Create the navigation panel factory.
	 * 
	 * @return the {@link NavigationPanelFactory}.
	 */
	public static NavigationPanelFactory getFactory() {
		return InjectorSingleton.injector
				.getInstance(NavigationPanelFactory.class);
	}

	private static class InjectorSingleton {
		static Injector injector = createInjector(new NavigationPanelModule());
	}

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(NavigationPanel.class,
				NavigationPanel.class).build(NavigationPanelFactory.class));
	}

}
