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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog;

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.spreadsheetimportdialog.panelproperties.panelproperties.SpreadsheetPanelProperties;

/**
 * Factory to create the properties worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
interface PropertiesWorkerFactory {

	/**
	 * Creates the properties worker for the field component.
	 * 
	 * @param field
	 *            the {@link FieldComponent}.
	 * 
	 * @param panelProperties
	 *            the CSV dialog {@link SpreadsheetPanelProperties}.
	 * 
	 * @return the {@link PropertiesWorker}.
	 */
	PropertiesWorker create(FieldComponent<?> field,
			SpreadsheetPanelProperties panelProperties);
}
