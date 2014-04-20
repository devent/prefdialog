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
package com.anrisoftware.prefdialog.tabspanel;

import static org.apache.commons.lang3.Validate.notNull;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationBean;
import com.anrisoftware.globalpom.reflection.exceptions.ReflectionError;

/**
 * Logging messages for {@link TabsPreferencesPanelField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class TabsPreferencesPanelFieldLogger extends AbstractLogger {

	private static final String ANNOTATION = "annotation";
	private static final String BEAN = "bean";
	private static final String PANEL = "panel";
	private static final String NO_FIELD_ANNOTATION_MESSAGE = "No field annotation found in %s.";
	private static final String NO_FIELD_ANNOTATION = "No field annotation found";
	private static final String NO_PREFERENCE_PANEL_SERVICE = "No preference panel service found";
	private static final String NO_PREFERENCE_PANEL_SERVICE_MESSAGE = "No preference panel service found for %s.";
	private static final String VETOED_VALUE = "Vetoed value to tab index %d for %s.";
	private static final String SET_RENDERER = "Set renderer {} to {}.";
	private static final String RENDERER_NULL = "Renderer cannot be null for %s.";

	/**
	 * Creates logger for {@link TabsPreferencesPanelField}.
	 */
	TabsPreferencesPanelFieldLogger() {
		super(TabsPreferencesPanelField.class);
	}

	ReflectionError noFieldAnnotationFound(TabsPreferencesPanelField panel,
			AnnotationBean bean) {
		return logException(
				new ReflectionError(NO_FIELD_ANNOTATION).add(PANEL, panel).add(
						BEAN, bean), NO_FIELD_ANNOTATION_MESSAGE, bean);
	}

	ReflectionError noPreferencePanelService(TabsPreferencesPanelField panel,
			Class<?> a) {
		return logException(new ReflectionError(NO_PREFERENCE_PANEL_SERVICE)
				.add(PANEL, panel).add(ANNOTATION, a),
				NO_PREFERENCE_PANEL_SERVICE_MESSAGE, a);
	}

	void valueVetoed(TabsPreferencesPanelField panel, int index) {
		log.debug(VETOED_VALUE, index, panel);
	}

	void checkRenderer(TabsPreferencesPanelField panel, TabsRenderer renderer) {
		notNull(renderer, RENDERER_NULL, panel);
	}

	void rendererSet(TabsPreferencesPanelField panel, TabsRenderer renderer) {
		log.debug(SET_RENDERER, renderer, panel);
	}

}
