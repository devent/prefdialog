package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.codec.Charsets;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class CsvImportModuleModule extends AbstractModule {

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

	@Provides
	@Singleton
	@Named("seperatorCharDefaults")
	Set<Character> getSeperatorCharDefaults() {
		Set<Character> list = new TreeSet<Character>();
		list.add('\t');
		list.add(',');
		list.add(';');
		list.add(' ');
		return list;
	}
}
