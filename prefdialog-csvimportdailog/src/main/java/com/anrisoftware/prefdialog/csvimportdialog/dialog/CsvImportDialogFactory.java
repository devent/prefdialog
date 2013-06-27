package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import javax.swing.JFrame;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;

public interface CsvImportDialogFactory {

	CsvImportDialog create(JFrame frame, CsvImportProperties properties);
}
