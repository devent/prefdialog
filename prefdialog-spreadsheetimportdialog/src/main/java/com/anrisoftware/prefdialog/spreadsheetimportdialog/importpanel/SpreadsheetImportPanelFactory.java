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

import javax.swing.JPanel;

import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImportProperties;
import com.anrisoftware.globalpom.spreadsheetimport.SpreadsheetImporterFactory;

/**
 * Factory to create the spreadsheet import panel.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
public interface SpreadsheetImportPanelFactory {

    /**
     * Creates the spreadsheet import panel in the specified container.
     *
     * @param container
     *            the {@link JPanel} container.
     *
     * @param properties
     *            the {@link SpreadsheetImportProperties}.
     *
     * @param importerFactory
     *            the {@link SpreadsheetImporterFactory}.
     *
     * @return the {@link SpreadsheetImportPanel}.
     */
    SpreadsheetImportPanel create(JPanel container,
            SpreadsheetImportProperties properties,
            SpreadsheetImporterFactory importerFactory);
}
