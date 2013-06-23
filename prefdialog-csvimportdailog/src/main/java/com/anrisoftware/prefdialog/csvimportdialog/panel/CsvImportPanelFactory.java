package com.anrisoftware.prefdialog.csvimportdialog.panel;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvProperties;

public interface CsvImportPanelFactory {

	CsvImportPanel create(CsvProperties properties);
}
