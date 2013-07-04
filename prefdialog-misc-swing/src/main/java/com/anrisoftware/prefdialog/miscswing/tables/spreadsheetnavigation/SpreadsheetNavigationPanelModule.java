package com.anrisoftware.prefdialog.miscswing.tables.spreadsheetnavigation;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs spreadsheet pane factory.
 * 
 * @see SpreadsheetNavigationPanel
 * @see SpreadsheetNavigationPanelFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class SpreadsheetNavigationPanelModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				SpreadsheetNavigationPanel.class,
				SpreadsheetNavigationPanel.class).build(
				SpreadsheetNavigationPanelFactory.class));
	}

}
