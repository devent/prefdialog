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
