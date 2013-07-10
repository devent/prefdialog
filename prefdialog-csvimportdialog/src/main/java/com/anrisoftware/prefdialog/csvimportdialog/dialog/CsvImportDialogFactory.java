package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import javax.swing.JFrame;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;

/**
 * Factory to create a new CSV import dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface CsvImportDialogFactory {

	/**
	 * Creates the import dialog for the specified frame.
	 * 
	 * @param frame
	 *            the {@link JFrame} for the dialog.
	 * 
	 * @param properties
	 *            the {@link CsvImportProperties}.
	 * 
	 * @return the {@link CsvImportDialog}.
	 */
	CsvImportDialog create(JFrame frame, CsvImportProperties properties);
}
