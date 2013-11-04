package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelProperties;
import com.anrisoftware.prefdialog.fields.FieldComponent;

/**
 * Factory to create the properties worker.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
interface PropertiesWorkerFactory {

	/**
	 * Creates the properties worker for the field component.
	 * 
	 * @param field
	 *            the {@link FieldComponent}.
	 * 
	 * @param panelProperties
	 *            the CSV dialog {@link CsvPanelProperties}.
	 * 
	 * @return the {@link PropertiesWorker}.
	 */
	PropertiesWorker create(FieldComponent<?> field,
			CsvPanelProperties panelProperties);
}
