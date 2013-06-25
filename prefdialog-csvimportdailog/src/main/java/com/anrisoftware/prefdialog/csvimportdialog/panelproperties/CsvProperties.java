package com.anrisoftware.prefdialog.csvimportdialog.panelproperties;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Locale;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanel;

public class CsvProperties implements CsvImportProperties {

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

	@FieldComponent(order = 0)
	@Child
	public ImportProperties getImportProperties() {
		return importProperties;
	}

	@FieldComponent(order = 1)
	@Child
	public SeparatorProperties getSeparatorProperties() {
		return separatorProperties;
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Charset getCharset() {
		return importProperties.getCharset();
	}

	@Override
	public Locale getLocale() {
		return importProperties.getLocale();
	}

	@Override
	public char getSeparator() {
		return separatorProperties.isUseCustomSeparator() ? separatorProperties
				.getCustomSeparatorChar() : separatorProperties
				.getSeparatorChar();
	}

	@Override
	public char getTextDelimiter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStartRow() {
		return importProperties.getStartRow();
	}

}
