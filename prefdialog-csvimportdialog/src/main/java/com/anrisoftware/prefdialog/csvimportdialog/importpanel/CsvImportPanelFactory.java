/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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

import javax.swing.JPanel;

import com.anrisoftware.globalpom.csvimport.CsvImportProperties;

/**
 * Factory to create the CSV import panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface CsvImportPanelFactory {

	/**
	 * Creates the CSV import panel in the specified container.
	 * 
	 * @param container
	 *            the {@link JPanel} container.
	 * 
	 * @param properties
	 *            the {@link CsvImportProperties}.
	 * 
	 * @return the {@link CsvImportPanel}.
	 */
	CsvImportPanel create(JPanel container, CsvImportProperties properties);
}
