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

import com.anrisoftware.globalpom.csvimport.CsvImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;

/**
 * Factory to create the spreadsheet import properties.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
public interface SpreadsheetPanelPropertiesFactory {

    /**
     * Creates default spreadsheet import properties.
     *
     * @return the {@link SpreadsheetPanelProperties}.
     */
    SpreadsheetPanelProperties create();

    /**
     * Creates the spreadsheet import properties by copying the properties.
     *
     * @param properties
     *            the {@link CsvImportProperties} which properties are copied.
     *
     * @return the {@link SpreadsheetPanelProperties}.
     */
    SpreadsheetPanelProperties create(SpreadsheetImportProperties properties);
}
