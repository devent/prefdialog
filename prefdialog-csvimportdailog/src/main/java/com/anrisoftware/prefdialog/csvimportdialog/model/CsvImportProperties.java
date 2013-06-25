package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * Properties to import a comma separated values file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface CsvImportProperties {

	/**
	 * Returns the path of the comma separated values file.
	 * 
	 * @return the {@link File} path.
	 */
	File getFile();

	/**
	 * Returns the character set of the file.
	 * 
	 * @return the {@link Charset}.
	 */
	Charset getCharset();

	/**
	 * Returns the locale of the file. The locale determines the format of
	 * numbers and dates.
	 * 
	 * @return the {@link Locale}.
	 */
	Locale getLocale();

	/**
	 * Returns the separator character.
	 * 
	 * @return the separator character.
	 */
	char getSeparator();

	/**
	 * Returns the text delimiter character.
	 * 
	 * @return the text delimiter character
	 */
	char getTextDelimiter();

	/**
	 * Returns the start row. Data from the file is read beginning with the
	 * start row.
	 * 
	 * @return the start row.
	 */
	int getStartRow();
}
