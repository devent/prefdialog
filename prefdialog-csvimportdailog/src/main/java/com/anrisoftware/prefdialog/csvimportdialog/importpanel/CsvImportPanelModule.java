package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import java.util.ServiceLoader;

import javax.inject.Singleton;

import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.CsvPanelPropertiesModule;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs CSV import panel factory and dependent modules.
 * 
 * @see CsvImportPanel
 * @see CsvImportPanelFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class CsvImportPanelModule extends AbstractModule {

	private static final String NO_SERVICE = "No service '%s'";
	private static final String VERTICAL_PREFERENCES_PANEL_NAME = "VerticalPreferencesPanel";

	@Override
	protected void configure() {
		install(new CsvPanelPropertiesModule());
		install(new FactoryModuleBuilder().implement(CsvImportPanel.class,
				CsvImportPanel.class).build(CsvImportPanelFactory.class));
	}

	@Provides
	@Singleton
	FieldService getVerticalPreferencesPanelFieldFactory() {
		for (FieldService service : ServiceLoader.load(FieldService.class)) {
			if (service.getInfo().getAnnotationType().getSimpleName()
					.equals(VERTICAL_PREFERENCES_PANEL_NAME)) {
				return service;
			}
		}
		addError(NO_SERVICE, VERTICAL_PREFERENCES_PANEL_NAME);
		return null;
	}

}
