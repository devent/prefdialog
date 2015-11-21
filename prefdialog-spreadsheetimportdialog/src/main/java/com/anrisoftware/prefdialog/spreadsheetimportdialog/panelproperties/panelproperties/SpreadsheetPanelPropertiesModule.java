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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties;

import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the import spreadsheet properties panel.
 *
 * @see SpreadsheetImportProperties
 * @see SpreadsheetPanelProperties
 * @see SpreadsheetPanelPropertiesFactory
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
public class SpreadsheetPanelPropertiesModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(
                SpreadsheetImportProperties.class,
                SpreadsheetPanelProperties.class).build(
                SpreadsheetPanelPropertiesFactory.class));
    }

}
