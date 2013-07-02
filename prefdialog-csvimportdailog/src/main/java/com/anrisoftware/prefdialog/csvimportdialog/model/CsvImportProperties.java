package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.io.File;
import java.net.URI;
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
	URI getFile();

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
	 * Returns the text quote character.
	 * 
	 * @return the text quote character
	 */
	char getQuote();

	/**
	 * Returns the symbols for a new line.
	 * 
	 * @return new line symbols {@link String}.
	 */
	String getEndOfLineSymbols();

	/**
	 * Returns the start row. Data from the file is read beginning with the
	 * start row.
	 * 
	 * @return the start row.
	 */
	int getStartRow();

	/**
	 * Returns the number of columns of the data.
	 * 
	 * @return the number of columns.
	 */
	int getNumCols();
}
