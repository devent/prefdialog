package com.anrisoftware.prefdialog.csvimportdialog.model;

/**
 * Factory to create a CSV data importer.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface CsvImporterFactory {

	/**
	 * Create the importer with the CSV import properties.
	 * 
	 * @param properties
	 *            the {@link CsvImportProperties}.
	 * 
	 * @return the {@link CsvImporter}.
	 */
	CsvImporter create(CsvImportProperties properties);
}
