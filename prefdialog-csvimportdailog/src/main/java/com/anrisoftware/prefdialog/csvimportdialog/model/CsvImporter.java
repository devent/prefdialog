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
	 * @return the column names and values {@link Map} of the read row.
	 */
	Map<String, Object> getMapValues();

	/**
	 * Returns the values of the read row.
	 * 
	 * @return the values {@link List} of the read row.
	 */
	List<Object> getValues();

	/**
	 * Reads the next row of the CSV formatted data.
	 */
	@Override
	CsvImporter call() throws Exception;
}
