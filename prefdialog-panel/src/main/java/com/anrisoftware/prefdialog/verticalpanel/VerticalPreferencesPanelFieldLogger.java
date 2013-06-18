/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.verticalpanel;

import java.beans.PropertyVetoException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.globalpom.reflection.exceptions.ReflectionError;

/**
 * Logging messages for {@link VerticalPreferencesPanelField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class VerticalPreferencesPanelFieldLogger extends AbstractLogger {

	private static final String VALUE_VETO_MESSAGE = "Value of first field veto for %s.";
	private static final String NO_PREFERENCE_PANEL_SERVICE = "No preference panel service found";
	private static final String NO_PREFERENCE_PANEL_SERVICE_MESSAGE = "No preference panel service found for %s.";
	private static final String ANNOTATION = "annotation";
	private static final String PANEL = "panel";

	/**
	 * Creates logger for {@link VerticalPreferencesPanelField}.
	 */
	VerticalPreferencesPanelFieldLogger() {
		super(VerticalPreferencesPanelField.class);
	}

	ReflectionError noPreferencePanelService(
			VerticalPreferencesPanelField panel, Class<?> a) {
		return logException(new ReflectionError(NO_PREFERENCE_PANEL_SERVICE)
				.addContextValue(PANEL, panel).addContextValue(ANNOTATION, a),
				NO_PREFERENCE_PANEL_SERVICE_MESSAGE, a);
	}

	void propertyVeto(VerticalPreferencesPanelField panel,
			PropertyVetoException e) {
		logException(e, VALUE_VETO_MESSAGE, panel);
	}

}
