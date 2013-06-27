package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.CsvProperties;

public interface CsvImportDialogFactory {

	CsvImportDialog create(JPanel container, CsvProperties properties);
}
