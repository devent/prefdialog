/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-csvimportdialog.
 * 
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import java.util.ServiceLoader;

import javax.inject.Singleton;

import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesModule;
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
 * @since 3.0
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
