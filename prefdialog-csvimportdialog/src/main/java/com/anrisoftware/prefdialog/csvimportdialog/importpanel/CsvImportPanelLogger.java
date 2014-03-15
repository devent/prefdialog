/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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

import java.beans.PropertyVetoException;

import com.anrisoftware.globalpom.dataimport.CsvImportException;
import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link CsvImportPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CsvImportPanelLogger extends AbstractLogger {

	private static final String ERROR_READ_MESSAGE = "Error read columns in {}.";
	private static final String ERROR_READ = "Error read columns";

	/**
	 * Creates a logger for {@link CsvImportPanel}.
	 */
	public CsvImportPanelLogger() {
		super(CsvImportPanel.class);
	}

	void errorRead(CsvImportPanel panel, CsvImportException e) {
		logException(new PropertyVetoException(ERROR_READ, null),
				ERROR_READ_MESSAGE, panel);
	}

}
