/*
 * Copyright 2012-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import static com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelProviderLogger._.service_found;
import static com.anrisoftware.prefdialog.verticalpanel.VerticalPreferencesPanelProviderLogger._.service_found_message;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link VerticalPreferencesPanelProvider}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Singleton
class VerticalPreferencesPanelProviderLogger extends AbstractLogger {

	enum _ {

		service_found_message("No service found '{}'."),

		service_found("No vertical preferences panel service found");

		private String name;

		private _(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	/**
	 * Creates a logger for {@link VerticalPreferencesPanelProvider}.
	 */
	public VerticalPreferencesPanelProviderLogger() {
		super(VerticalPreferencesPanelProvider.class);
	}

	IllegalStateException errorFindService(String name) {
		return logException(
				new IllegalStateException(service_found.toString()),
				service_found_message, name);
	}

}
