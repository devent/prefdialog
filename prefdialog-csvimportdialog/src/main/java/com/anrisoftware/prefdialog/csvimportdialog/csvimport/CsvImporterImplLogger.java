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
package com.anrisoftware.prefdialog.csvimportdialog.csvimport;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportException;

/**
 * Logging messages for {@link CsvImporterImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CsvImporterImplLogger extends AbstractLogger {

	private static final String IMPORTER = "importer";
	private static final String ERROR_OPEN_FILE_MESSAGE = "Error open file '{}'.";
	private static final String ERROR_OPEN_FILE = "Error open file";
	private static final String ERROR_READ = "Error read file";
	private static final String ERROR_READ_MESSAGE = "Error read file '{}'.";

	/**
	 * Creates a logger for {@link CsvImporterImpl}.
	 */
	public CsvImporterImplLogger() {
		super(CsvImporterImpl.class);
	}

	CsvImportException errorOpenFile(CsvImporterImpl importer, Exception e) {
		return logException(
				new CsvImportException(ERROR_OPEN_FILE, e).addContext(IMPORTER,
						importer), ERROR_OPEN_FILE_MESSAGE, importer
						.getProperties().getFile());
	}

	CsvImportException errorRead(CsvImporterImpl importer, IOException e) {
		return logException(new CsvImportException(ERROR_READ, e).addContext(
				IMPORTER, importer), ERROR_READ_MESSAGE, importer
				.getProperties().getFile());
	}

}
