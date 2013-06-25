package com.anrisoftware.prefdialog.csvimportdialog.panel;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.CsvProperties;

public interface CsvImportPanelFactory {

	CsvImportPanel create(JPanel container, CsvProperties properties);
}
