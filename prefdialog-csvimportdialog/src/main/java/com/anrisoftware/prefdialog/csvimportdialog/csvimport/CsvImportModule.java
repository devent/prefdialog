package com.anrisoftware.prefdialog.csvimportdialog.csvimport;

import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImporter;
import com.anrisoftware.prefdialog.csvimportdialog.model.CsvImporterFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs CSV importer factory.
 * 
 * @see CsvImporter
 * @see CsvImporterFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class CsvImportModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(CsvImporter.class,
				CsvImporterImpl.class).build(CsvImporterFactory.class));
	}

}
