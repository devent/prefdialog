/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import java.beans.PropertyVetoException;
import java.io.File;
import java.net.URI;

import javax.inject.Inject;

import com.anrisoftware.globalpom.csvimport.CsvImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties.LineEnd;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties.QuoteCharModel;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelProperties;
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.separatorproperties.SeparatorCharModel;
import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.filechoosers.FileChooserModel;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the properties for the field.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class PropertiesWorker {

	private static final String CUSTOM_SEPARATOR_CHAR_FIELD = "customSeparatorChar";

	private static final String USE_CUSTOM_SEPARATOR_FIELD = "useCustomSeparator";

	private static final String SEPARATOR_CHAR_FIELD = "separatorChar";

	private static final String LINE_END_SYMBOLS_FIELD = "lineEndSymbols";

	private static final String CHARSET_FIELD = "charset";

	private static final String FILE_FIELD = "file";

	private static final String START_ROW_FIELD = "startRow";

	private static final String NUM_COLS_FIELD = "numberColumns";

	private static final String LOCALE_FIELD = "locale";

	private static final String CUSTOM_QUOTE_CHAR_FIELD = "customQuoteChar";

	private static final String USE_CUSTOM_QUOTE_FIELD = "useCustomQuote";

	private static final String QUOTE_CHAR_FIELD = "quoteChar";

	private final FieldComponent<?> field;

	private final CsvPanelProperties panelProperties;

	/**
	 * @see PropertiesWorkerFactory#create(FieldComponent, CsvPanelProperties)
	 */
	@Inject
	PropertiesWorker(@Assisted FieldComponent<?> field,
			@Assisted CsvPanelProperties panelProperties) {
		this.field = field;
		this.panelProperties = panelProperties;
	}

	/**
	 * @see CsvImportDialog#setProperties(CsvImportProperties)
	 */
	@OnAwt
	public void setProperties(CsvImportProperties properties)
			throws PropertyVetoException {
		setCharsetProperty(properties, field);
		setLineEndProperty(properties, field);
		setFileProperty(properties, field);
		setModelFileProperty(properties);
		setLocaleProperty(properties, field);
		setNumColsProperty(properties, field);
		setQuoteProperty(properties, field);
		setSeparatorProperty(properties, field);
		setStartRowProperty(properties, field);
	}

	/**
	 * @see CsvImportDialog#setProperties(CsvImportProperties)
	 */
	@OnAwt
	public void setPropertiesNoChecks(CsvImportProperties properties) {
		try {
			setCharsetProperty(properties, field);
		} catch (PropertyVetoException e) {
		}
		try {
			setLineEndProperty(properties, field);
		} catch (PropertyVetoException e) {
		}
		try {
			setFileProperty(properties, field);
		} catch (PropertyVetoException e) {
		}
		try {
			setModelFileProperty(properties);
		} catch (PropertyVetoException e) {
		}
		try {
			setLocaleProperty(properties, field);
		} catch (PropertyVetoException e) {
		}
		try {
			setNumColsProperty(properties, field);
		} catch (PropertyVetoException e) {
		}
		try {
			setQuoteProperty(properties, field);
		} catch (PropertyVetoException e) {
		}
		try {
			setSeparatorProperty(properties, field);
		} catch (PropertyVetoException e) {
		}
		try {
			setStartRowProperty(properties, field);
		} catch (PropertyVetoException e) {
		}
	}

	private void setQuoteProperty(CsvImportProperties properties,
			FieldComponent<?> field) throws PropertyVetoException {
		QuoteCharModel model = panelProperties.getAdvancedProperties()
				.getQuoteCharModel();
		char quote = properties.getQuote();
		FieldComponent<?> f;
		if (model.isQuoteChar(quote)) {
			f = field.findField(QUOTE_CHAR_FIELD);
			f.setValue(properties.getQuote());
		} else {
			f = field.findField(USE_CUSTOM_QUOTE_FIELD);
			f.setValue(true);
			f = field.findField(CUSTOM_QUOTE_CHAR_FIELD);
			f.setValue(properties.getQuote());
		}
	}

	private void setNumColsProperty(CsvImportProperties properties,
			FieldComponent<?> field) throws PropertyVetoException {
		FieldComponent<?> f = field.findField(NUM_COLS_FIELD);
		f.setValue(properties.getLocale());
	}

	private void setStartRowProperty(CsvImportProperties properties,
			FieldComponent<?> field) throws PropertyVetoException {
		FieldComponent<?> f = field.findField(START_ROW_FIELD);
		f.setValue(properties.getStartRow());
	}

	private void setLocaleProperty(CsvImportProperties properties,
			FieldComponent<?> field) throws PropertyVetoException {
		FieldComponent<?> f = field.findField(LOCALE_FIELD);
		f.setValue(properties.getLocale());
	}

	private void setFileProperty(CsvImportProperties properties,
			FieldComponent<?> field) throws PropertyVetoException {
		FieldComponent<?> f = field.findField(FILE_FIELD);
		f.setValue(new File(properties.getFile()));
	}

	private void setModelFileProperty(CsvImportProperties properties)
			throws PropertyVetoException {
		FileChooserModel model = panelProperties.getFileProperties()
				.getFileModel();
		URI file = properties.getFile();
		if (file != null) {
			model.setFile(new File(file));
		}
	}

	private void setCharsetProperty(CsvImportProperties properties,
			FieldComponent<?> field) throws PropertyVetoException {
		FieldComponent<?> f = field.findField(CHARSET_FIELD);
		f.setValue(properties.getCharset());
	}

	private void setLineEndProperty(CsvImportProperties properties,
			FieldComponent<?> field) throws PropertyVetoException {
		FieldComponent<?> f = field.findField(LINE_END_SYMBOLS_FIELD);
		LineEnd symbols = LineEnd.parse(properties.getEndOfLineSymbols());
		f.setValue(symbols);
	}

	private void setSeparatorProperty(CsvImportProperties properties,
			FieldComponent<?> field) throws PropertyVetoException {
		SeparatorCharModel model = panelProperties.getSeparatorProperties()
				.getSeparatorCharModel();
		char separator = properties.getSeparator();
		if (model.isSeparator(separator)) {
			field.findField(SEPARATOR_CHAR_FIELD).setValue(separator);
		} else {
			field.findField(USE_CUSTOM_SEPARATOR_FIELD).setValue(true);
			field.findField(CUSTOM_SEPARATOR_CHAR_FIELD).setValue(separator);
		}
	}

}
