package com.anrisoftware.prefdialog.csvimportdialog.panelproperties;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;

/**
 * Factory to create the CSV import properties.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface CsvPanelPropertiesFactory {

	/**
	 * Creates default CSV import properties.
	 * 
	 * @return the {@link CsvPanelProperties}.
	 */
	CsvPanelProperties create();

	/**
	 * Creates the CSV import properties by copying the properties.
	 * 
	 * @param properties
	 *            the {@link CsvImportProperties} which properties are copied.
	 * 
	 * @return the {@link CsvPanelProperties}.
	 */
	CsvPanelProperties create(CsvImportProperties properties);
}
