package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;

public interface CsvImportPanelFactory {

	CsvImportPanel create(JPanel container, CsvImportProperties properties);
}
