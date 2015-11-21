/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-spreadsheetimportdialog.
 *
 * prefdialog-spreadsheetimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-spreadsheetimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-spreadsheetimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.spreadsheetimportdialog.importpanel;

import com.anrisoftware.globalpom.csvimport.CsvImportModule;
import com.anrisoftware.prefdialog.core.CoreFieldComponentModule;
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ComboBoxHistoryModule;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelPropertiesFactory;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelPropertiesModule;
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs CSV import panel factory and dependent modules.
 * 
 * @see SpreadsheetImportPanel
 * @see SpreadsheetImportPanelFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class SpreadsheetImportPanelModule extends AbstractModule {

	/**
	 * @see #getFactory()
	 */
	public static SpreadsheetImportPanelFactory getCsvImportPanelFactory() {
		return getFactory();
	}

	/**
	 * Creates and returns the import panel factory.
	 * 
	 * @return the {@link SpreadsheetImportPanelFactory}.
	 */
	public static SpreadsheetImportPanelFactory getFactory() {
		return SingletonHolder.factory;
	}

	/**
	 * @see #getPropertiesFactory()
	 */
	public static SpreadsheetPanelPropertiesFactory getCsvImportPropertiesFactory() {
		return getPropertiesFactory();
	}

	/**
	 * Creates and returns the panel properties factory.
	 * 
	 * @return the {@link SpreadsheetPanelPropertiesFactory}.
	 */
	public static SpreadsheetPanelPropertiesFactory getPropertiesFactory() {
		return SingletonHolder.propertiesFactory;
	}

	private static class SingletonHolder {

		public static final Injector injector = Guice.createInjector(
				new SpreadsheetImportPanelModule(), new CoreFieldComponentModule(),
				new TextsResourcesDefaultModule(), new ComboBoxHistoryModule(),
				new CsvImportModule());

		public static final SpreadsheetImportPanelFactory factory = injector
				.getInstance(SpreadsheetImportPanelFactory.class);

		public static final SpreadsheetPanelPropertiesFactory propertiesFactory = injector
				.getInstance(SpreadsheetPanelPropertiesFactory.class);
	}

	@Override
	protected void configure() {
		install(new SpreadsheetPanelPropertiesModule());
		install(new FactoryModuleBuilder().implement(SpreadsheetImportPanel.class,
				SpreadsheetImportPanel.class).build(SpreadsheetImportPanelFactory.class));
	}
}
