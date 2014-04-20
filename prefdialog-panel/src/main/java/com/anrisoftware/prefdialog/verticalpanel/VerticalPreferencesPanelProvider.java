/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.verticalpanel;

import static java.util.ServiceLoader.load;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.Provider;

/**
 * Provides the vertical preferences panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Singleton
public class VerticalPreferencesPanelProvider implements Provider<FieldService> {

	private static final String PREFERENCES_PANEL_NAME = "VerticalPreferencesPanel";

	private final VerticalPreferencesPanelProviderLogger log;

	private final FieldService service;

	@Inject
	VerticalPreferencesPanelProvider(VerticalPreferencesPanelProviderLogger log) {
		this.log = log;
		this.service = findService();
	}

	@Override
	public FieldService get() {
		return service;
	}

	private FieldService findService() {
		for (FieldService service : load(FieldService.class)) {
			if (isPreferencesPanelService(service)) {
				return service;
			}
		}
		throw log.errorFindService(PREFERENCES_PANEL_NAME);
	}

	private boolean isPreferencesPanelService(FieldService service) {
		return service.getInfo().getAnnotationType().getSimpleName()
				.equals(PREFERENCES_PANEL_NAME);
	}
}
