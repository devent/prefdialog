package com.anrisoftware.prefdialog.csvimportdialog.panelproperties;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Locale;

import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.annotations.FieldComponent;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanel;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

public class CsvPanelProperties implements CsvImportProperties {

	private Object importPanel;

	private final FileProperties fileProperties;

	private final ImportProperties importProperties;

	private final SeparatorProperties separatorProperties;

	/**
	 * @see CsvPanelPropertiesFactory#create()
	 */
	@AssistedInject
	CsvPanelProperties(FileProperties fileProperties,
			ImportProperties importProperties,
			SeparatorProperties separatorProperties) {
		this(fileProperties, importProperties, separatorProperties, null);
	}

	/**
	 * @see CsvPanelPropertiesFactory#create(CsvImportProperties)
	 */
	@AssistedInject
	CsvPanelProperties(FileProperties fileProperties,
			ImportProperties importProperties,
			SeparatorProperties separatorProperties,
			@Assisted CsvImportProperties properties) {
		this.fileProperties = fileProperties;
		this.importProperties = importProperties;
		this.separatorProperties = separatorProperties;
		setupProperties(properties);
	}

	private void setupProperties(CsvImportProperties properties) {
		if (properties == null) {
			return;
		}
		fileProperties.setFile(properties.getFile());
		importProperties.setCharset(properties.getCharset());
		importProperties.setLocale(properties.getLocale());
		if (separatorProperties.getSeparatorCharModel().getIndexOf(
				properties.getSeparator()) != -1) {
			separatorProperties.setSeparatorChar(properties.getSeparator());
		} else {
			separatorProperties.setUseCustomSeparator(true);
			separatorProperties.setCustomSeparatorChar(properties
					.getSeparator());
		}
		importProperties.setStartRow(properties.getStartRow());
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
	public char getTextDelimiter() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStartRow() {
		return importProperties.getStartRow();
	}

}
