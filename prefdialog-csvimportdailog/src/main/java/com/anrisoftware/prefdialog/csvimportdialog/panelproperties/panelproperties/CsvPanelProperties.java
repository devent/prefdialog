package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Locale;

import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties.AdvancedProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties.LineEnd;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.fileproperties.FileProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.importproperties.ImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.separatorproperties.SeparatorProperties;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanel;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

public class CsvPanelProperties implements CsvImportProperties {

	private Object importPanel;

	private final FileProperties fileProperties;

	private final ImportProperties importProperties;

	private final SeparatorProperties separatorProperties;

	private final AdvancedProperties advancedProperties;

	/**
	 * @see CsvPanelPropertiesFactory#create()
	 */
	@AssistedInject
	CsvPanelProperties(FileProperties fileProperties,
			ImportProperties importProperties,
			SeparatorProperties separatorProperties,
			AdvancedProperties advancedProperties) {
		this.fileProperties = fileProperties;
		this.importProperties = importProperties;
		this.separatorProperties = separatorProperties;
		this.advancedProperties = advancedProperties;
	}

	/**
	 * @see CsvPanelPropertiesFactory#create(CsvImportProperties)
	 */
	@AssistedInject
	CsvPanelProperties(FileProperties fileProperties,
			ImportProperties importProperties,
			SeparatorProperties separatorProperties,
			AdvancedProperties advancedProperties,
			@Assisted CsvImportProperties properties) {
		this.fileProperties = fileProperties;
		this.importProperties = importProperties;
		this.separatorProperties = separatorProperties;
		this.advancedProperties = advancedProperties;
		setupProperties(properties);
	}

	private void setupProperties(CsvImportProperties properties) {
		if (properties == null) {
			return;
		}
		fileProperties.setFile(properties.getFile());
		importProperties.setCharset(properties.getCharset());
		importProperties.setLocale(properties.getLocale());
		importProperties.setStartRow(properties.getStartRow());
		separatorProperties.setupProperties(properties);
		advancedProperties.setupProperties(properties);
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
	public FileProperties getFileProperties() {
		return fileProperties;
	}

	@FieldComponent(order = 1)
	@Child
	public ImportProperties getImportProperties() {
		return importProperties;
	}

	@FieldComponent(order = 2)
	@Child
	public SeparatorProperties getSeparatorProperties() {
		return separatorProperties;
	}

	@FieldComponent(order = 3)
	@Child
	public AdvancedProperties getAdvancedProperties() {
		return advancedProperties;
	}

	@Override
	public File getFile() {
		return fileProperties.getFile();
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
	public char getQuote() {
		return advancedProperties.isUseCustomQuote() ? advancedProperties
				.getCustomQuoteChar() : advancedProperties.getQuoteChar();
	}

	@Override
	public String getEndOfLineSymbols() {
		LineEnd lineEnd = advancedProperties.getLineEndSymbols();
		switch (lineEnd) {
		case DEFAULT:
			return System.getProperty("line.separator");
		default:
			return lineEnd.getSymbols();
		}
	}

	@Override
	public int getStartRow() {
		return importProperties.getStartRow();
	}

}
