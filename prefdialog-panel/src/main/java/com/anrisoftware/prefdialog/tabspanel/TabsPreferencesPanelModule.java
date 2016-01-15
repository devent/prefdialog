/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.tabspanel;

import java.util.ServiceLoader;

import javax.inject.Singleton;
import javax.swing.JTabbedPane;

import com.anrisoftware.prefdialog.fields.FieldComponent;
import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the tabs preferences panel factory and dependencies.
 * 
 * @see TabsPreferencesPanelField
 * @see TabsPreferencesPanelFieldFactory
 * @see FieldFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class TabsPreferencesPanelModule extends AbstractModule {

	private static final String NO_SERVICE = "Could not find service '%s'.";
	private static final String PREFERENCE_PANEL_NAME = "PreferencesPanel";

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				new TypeLiteral<FieldComponent<JTabbedPane>>() {
				}, TabsPreferencesPanelField.class).build(
				TabsPreferencesPanelFieldFactory.class));
		bind(FieldFactory.class).to(TabsPreferencesPanelFieldFactory.class);
	}

	@Provides
	@Singleton
	FieldService getPreferencesPanelService() {
		for (FieldService service : ServiceLoader.load(FieldService.class)) {
			if (service.getInfo().getAnnotationType().getSimpleName()
					.equals(PREFERENCE_PANEL_NAME)) {
				return service;
			}
		}
		addError(NO_SERVICE, PREFERENCE_PANEL_NAME);
		return null;
	}

}
