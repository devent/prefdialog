package com.anrisoftware.prefdialog.csvimportdialog.panelproperties;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.codec.Charsets;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImportProperties;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the properties factory and provides the default character sets.
 * 
 * @see CsvImportProperties
 * @see CsvPanelProperties
 * @see CsvPanelPropertiesFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class CsvPanelPropertiesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(CsvImportProperties.class,
				CsvPanelProperties.class).build(CsvPanelPropertiesFactory.class));
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
