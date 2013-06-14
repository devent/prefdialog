package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs spreadsheet table factories.
 * 
 * @see SpreadsheetTable
 * @see SpreadsheetTableFactory
 * @see SpreadsheetTableModel
 * @see SpreadsheetTableModelFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class SpreadsheetTableModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(SpreadsheetTable.class,
				SpreadsheetTable.class).build(SpreadsheetTableFactory.class));
		install(new FactoryModuleBuilder().implement(
				SpreadsheetTableModel.class, SpreadsheetTableModel.class)
				.build(SpreadsheetTableModelFactory.class));
	}

}
