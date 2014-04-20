/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.commons.codec.Charsets;

import com.anrisoftware.globalpom.dataimport.CsvImportProperties;
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
				CsvPanelProperties.class)
				.build(CsvPanelPropertiesFactory.class));
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
