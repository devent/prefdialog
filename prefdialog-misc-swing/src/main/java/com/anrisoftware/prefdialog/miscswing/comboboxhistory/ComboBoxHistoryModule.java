/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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
package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import static com.google.inject.Guice.createInjector;

import javax.swing.ListCellRenderer;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ComboBoxHistoryModule extends AbstractModule {

	private static Injector injector;

	public static Injector getInjector() {
		if (injector == null) {
			synchronized (ComboBoxHistoryModule.class) {
				injector = createInjector(new ComboBoxHistoryModule());
			}
		}
		return injector;
	}

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				HistoryComboBoxModel.class, HistoryComboBoxModel.class).build(
				HistoryComboBoxModelFactory.class));
		install(new FactoryModuleBuilder().implement(HistoryComboBox.class,
				HistoryComboBox.class).build(HistoryComboBoxFactory.class));
		install(new FactoryModuleBuilder().implement(ItemDefault.class,
				ItemDefault.class).build(ItemDefaultFactory.class));
		install(new FactoryModuleBuilder().implement(ListCellRenderer.class,
				ItemsDefaultComboBoxRenderer.class).build(
				ItemsDefaultComboBoxRendererFactory.class));
	}

}
