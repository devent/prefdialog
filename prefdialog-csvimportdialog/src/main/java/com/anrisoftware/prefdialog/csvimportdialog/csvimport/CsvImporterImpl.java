/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.csvimportdialog.csvimport;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportException;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImporter;
import com.google.inject.assistedinject.Assisted;

/**
 * Import CSV data based on CSV import properties.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class CsvImporterImpl implements CsvImporter {

	private static final String FILE = "file";

	private final CsvImportProperties properties;

	private final CsvImporterImplLogger log;

	private CsvListReader reader;

	private int readLines;

	private List<Object> values;

	@Inject
	CsvImporterImpl(CsvImporterImplLogger logger,
			@Assisted CsvImportProperties properties) {
		this.log = logger;
		this.properties = properties;
		this.readLines = 0;
	}

	@Override
	public CsvImportProperties getProperties() {
		return properties;
	}

	@Override
	public Map<String, Object> getMapValues() {
		return null;
	}

	@Override
	public List<Object> getValues() {
		return values;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CsvImporter call() throws CsvImportException {
		CsvListReader reader = getReader();
		readHead(reader);
		values = read(reader);
		return this;
	}

	private void readHead(CsvListReader reader) throws CsvImportException {
		for (; readLines < properties.getStartRow(); readLines++) {
			read(reader);
		}
	}

	@SuppressWarnings("rawtypes")
	private List read(CsvListReader reader) throws CsvImportException {
		try {
			return reader.read();
		} catch (IOException e) {
			throw log.errorRead(this, e);
		}
	}

	private CsvListReader getReader() throws CsvImportException {
		if (reader == null) {
			reader = createReader();
		}
		return reader;
	}

	private CsvListReader createReader() throws CsvImportException {
		char quote = properties.getQuote();
		int separator = properties.getSeparator();
		String end = properties.getEndOfLineSymbols();
		return new CsvListReader(openFile(), new CsvPreference.Builder(quote,
				separator, end).build());
	}

	private Reader openFile() throws CsvImportException {
		Charset cs = properties.getCharset();
		return new InputStreamReader(openFileStream(), cs);
	}

	private InputStream openFileStream() throws CsvImportException {
		try {
			return properties.getFile().toURL().openStream();
		} catch (MalformedURLException e) {
			throw log.errorOpenFile(this, e);
		} catch (IOException e) {
			throw log.errorOpenFile(this, e);
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(FILE, properties.getFile())
				.toString();
	}
}
