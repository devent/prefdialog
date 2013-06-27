package com.anrisoftware.prefdialog.csvimportdialog.panelproperties;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.codec.Charsets;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * Provides the default character sets.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class CsvImportPropertiesModule extends AbstractModule {

	@Override
	protected void configure() {
	}

	@Provides
	@Singleton
	@Named("charsetDefaults")
	Collection<Charset> getCharsetDefaults() {
		List<Charset> list = new ArrayList<Charset>();
		list.add(Charsets.UTF_8);
		list.add(Charsets.UTF_16);
		list.add(Charsets.UTF_16BE);
		list.add(Charsets.UTF_16LE);
		list.add(Charsets.US_ASCII);
		list.add(Charsets.ISO_8859_1);
		return list;
	}

}
