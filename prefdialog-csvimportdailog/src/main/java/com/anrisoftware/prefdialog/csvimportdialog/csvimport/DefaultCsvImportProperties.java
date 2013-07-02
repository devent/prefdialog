package com.anrisoftware.prefdialog.csvimportdialog.csvimport;

import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Locale;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;

/**
 * Mutable CSV import properties.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class DefaultCsvImportProperties implements CsvImportProperties {

	private URI file;

	private Charset charset;

	private Locale locale;

	private char separator;

	private char quote;

	private String endOfLineSymbols;

	private int startRow;

	private int numCols;

	/**
	 * Sets system based default values.
	 */
	public DefaultCsvImportProperties() {
		this.file = new File("").toURI();
		this.charset = Charset.defaultCharset();
		this.locale = Locale.US;
		this.separator = ',';
		this.quote = '"';
		this.endOfLineSymbols = System.getProperty("line.separator");
		this.startRow = 0;
		this.numCols = 0;
	}

	public void setFile(URI file) {
		this.file = file;
	}

	@Override
	public URI getFile() {
		return file;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	@Override
	public Charset getCharset() {
		return charset;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	@Override
	public Locale getLocale() {
		return locale;
	}

	public void setSeparator(char separator) {
		this.separator = separator;
	}

	@Override
	public char getSeparator() {
		return separator;
	}

	public void setQuote(char quote) {
		this.quote = quote;
	}

	@Override
	public char getQuote() {
		return quote;
	}

	public void setEndOfLineSymbols(String symbols) {
		this.endOfLineSymbols = symbols;
	}

	@Override
	public String getEndOfLineSymbols() {
		return endOfLineSymbols;
	}

	public void setStartRow(int row) {
		this.startRow = row;
	}

	@Override
	public int getStartRow() {
		return startRow;
	}

	public void setNumCols(int numCols) {
		this.numCols = numCols;
	}

	@Override
	public int getNumCols() {
		return numCols;
	}
}
