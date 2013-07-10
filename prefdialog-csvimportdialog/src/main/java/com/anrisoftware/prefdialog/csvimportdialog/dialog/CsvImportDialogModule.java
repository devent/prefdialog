package com.anrisoftware.prefdialog.csvimportdialog.dialog;

import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanel;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanelFactory;
import com.anrisoftware.prefdialog.csvimportdialog.importpanel.CsvImportPanelModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs CSV import dialog factory and dependent modules.
 * 
 * @see CsvImportPanel
 * @see CsvImportPanelFactory
 * @see CsvImportDialogModule
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class CsvImportDialogModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new CsvImportPanelModule());
		install(new FactoryModuleBuilder().implement(CsvImportDialog.class,
				CsvImportDialog.class).build(CsvImportDialogFactory.class));
	}

}
