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
package com.anrisoftware.prefdialog.panel;

import java.lang.annotation.Annotation;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationBean;
import com.anrisoftware.globalpom.reflection.exceptions.ReflectionError;

/**
 * Logging messages for {@link PreferencesPanelField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class PreferencesPanelFieldLogger extends AbstractLogger {

	private static final String TYPE = "type";
	private static final String BEAN = "bean";
	private static final String PANEL = "panel";
	private static final String NO_FIELD_ANNOTATION_MESSAGE = "No field annotation found in %s.";
	private static final String NO_FIELD_ANNOTATION = "No field annotation found";
	private static final String NO_SERVICE = "No field service found";
	private static final String NO_SERVICE_MESSAGE = "No field service found for %s.";

	/**
	 * Creates logger for {@link PreferencesPanelField}.
	 */
	PreferencesPanelFieldLogger() {
		super(PreferencesPanelField.class);
	}

	ReflectionError noFieldAnnotationFound(PreferencesPanelField panel,
			AnnotationBean bean) {
		return logException(new ReflectionError(NO_FIELD_ANNOTATION)
				.addContextValue(PANEL, panel).addContextValue(BEAN, bean),
				NO_FIELD_ANNOTATION_MESSAGE, bean);
	}

	ReflectionError noFieldServiceFound(PreferencesPanelField panel,
			Class<? extends Annotation> type) {
		return logException(
				new ReflectionError(NO_SERVICE).addContextValue(PANEL, panel)
						.addContextValue(TYPE, type), NO_SERVICE_MESSAGE, type);
	}
}
