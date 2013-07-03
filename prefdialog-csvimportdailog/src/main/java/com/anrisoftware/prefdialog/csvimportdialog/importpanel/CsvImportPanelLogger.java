package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import java.beans.PropertyVetoException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportException;

/**
 * Logging messages for {@link CsvImportPanel}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
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
