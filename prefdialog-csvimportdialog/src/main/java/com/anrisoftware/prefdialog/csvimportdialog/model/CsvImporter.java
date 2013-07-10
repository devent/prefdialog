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
package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Reads CSV formatted data.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface CsvImporter extends Callable<CsvImporter> {

	/**
	 * Returns the import properties.
	 * 
	 * @return the {@link CsvImportProperties}.
	 */
	CsvImportProperties getProperties();

	/**
	 * Returns the values of the read row with the column name as the map key
	 * and the column value as the map value.
	 * 
	 * @return the column names and values {@link Map} of the read row or
	 *         {@code null} if the end of file was reached.
	 */
	Map<String, Object> getMapValues();

	/**
	 * Returns the values of the read row.
	 * 
	 * @return the values {@link List} of the read row or {@code null} if the
	 *         end of file was reached.
	 */
	List<Object> getValues();

	/**
	 * Reads the next row of the CSV formatted data.
	 */
	@Override
	CsvImporter call() throws CsvImportException;
}
