package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import javax.swing.JFrame;

import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.CsvPanelProperties;

public interface CsvImportDialogFactory {

	CsvImportDialog create(JFrame frame, CsvPanelProperties properties);
}
