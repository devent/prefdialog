package com.anrisoftware.prefdialog.csvimportdialog.model;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanel;

public class CsvProperties {

	private Object importPanel;

	private final ImportProperties importProperties;

	private final SeparatorProperties separatorProperties;

	@Inject
	CsvProperties(ImportProperties importProperties,
			SeparatorProperties separatorProperties) {
		this.importProperties = importProperties;
		this.separatorProperties = separatorProperties;
	}

	public void setImportPanel(Object importPanel) {
		this.importPanel = importPanel;
	}

	@FieldComponent
	@VerticalPreferencesPanel
	public Object getImportPanel() {
		return importPanel;
	}

	@FieldComponent
	@Child
	public ImportProperties getImportProperties() {
		return importProperties;
	}

	@FieldComponent
	@Child
	public SeparatorProperties getSeparatorProperties() {
		return separatorProperties;
	}

}
