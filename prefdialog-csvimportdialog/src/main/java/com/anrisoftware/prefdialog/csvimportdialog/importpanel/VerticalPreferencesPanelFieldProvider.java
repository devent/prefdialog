/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import static java.util.ServiceLoader.load;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

import com.anrisoftware.prefdialog.fields.FieldService;

/**
 * Provides the vertical preferences panel field service.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Singleton
class VerticalPreferencesPanelFieldProvider implements Provider<FieldService> {

	private static final String VERTICAL_PREFERENCES_PANEL_NAME = "VerticalPreferencesPanel";

	@Inject
	private VerticalPreferencesPanelFieldProviderLogger log;

	private final FieldService service;

	VerticalPreferencesPanelFieldProvider() {
		this.service = findService();
	}

	@Override
	public FieldService get() {
		return service;
	}

	private FieldService findService() {
		for (FieldService service : load(FieldService.class)) {
			if (service.getInfo().getAnnotationType().getSimpleName()
					.equals(VERTICAL_PREFERENCES_PANEL_NAME)) {
				return service;
			}
		}
		throw log.noService(this, VERTICAL_PREFERENCES_PANEL_NAME);
	}

}
