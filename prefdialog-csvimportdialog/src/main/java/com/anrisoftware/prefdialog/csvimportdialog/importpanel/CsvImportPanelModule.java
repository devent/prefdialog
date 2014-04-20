/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import com.anrisoftware.globalpom.dataimport.CsvImportModule;
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesModule;
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule;
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs CSV import panel factory and dependent modules.
 * 
 * @see CsvImportPanel
 * @see CsvImportPanelFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class CsvImportPanelModule extends AbstractModule {

	/**
	 * @see #getFactory()
	 */
	public static CsvImportPanelFactory getCsvImportPanelFactory() {
		return getFactory();
	}

	/**
	 * Creates and returns the import panel factory.
	 * 
	 * @return the {@link CsvImportPanelFactory}.
	 */
	public static CsvImportPanelFactory getFactory() {
		return SingletonHolder.factory;
	}

	/**
	 * @see #getPropertiesFactory()
	 */
	public static CsvPanelPropertiesFactory getCsvImportPropertiesFactory() {
		return getPropertiesFactory();
	}

	/**
	 * Creates and returns the panel properties factory.
	 * 
	 * @return the {@link CsvPanelPropertiesFactory}.
	 */
	public static CsvPanelPropertiesFactory getPropertiesFactory() {
		return SingletonHolder.propertiesFactory;
	}

	private static class SingletonHolder {

		public static final Injector injector = Guice.createInjector(
				new CsvImportPanelModule(), new CoreFieldComponentModule(),
				new TextsResourcesDefaultModule(), new ComboBoxHistoryModule(),
				new CsvImportModule());

		public static final CsvImportPanelFactory factory = injector
				.getInstance(CsvImportPanelFactory.class);

		public static final CsvPanelPropertiesFactory propertiesFactory = injector
				.getInstance(CsvPanelPropertiesFactory.class);
	}

	@Override
	protected void configure() {
		install(new CsvPanelPropertiesModule());
		install(new FactoryModuleBuilder().implement(CsvImportPanel.class,
				CsvImportPanel.class).build(CsvImportPanelFactory.class));
	}
}
