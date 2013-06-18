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
package com.anrisoftware.prefdialog.fields.spinner;

import static org.apache.commons.lang3.Validate.notNull;

import javax.swing.SpinnerModel;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link SpinnerField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SpinnerFieldLogger extends AbstractLogger {

	private static final String SPINNER_MODEL_SET_MESSAGE = "Spinner model {} set for field '{}'.";
	private static final String SPINNER_MODEL_SET = "Spinner model {} set for {}.";
	private static final String SPINNER_MODEL_NULL = "Spinner model cannot be null for %s.";

	/**
	 * Creates logger for {@link SpinnerField}.
	 */
	SpinnerFieldLogger() {
		super(SpinnerField.class);
	}

	void checkModel(SpinnerField field, SpinnerModel model) {
		notNull(model, SPINNER_MODEL_NULL, field);
	}

	void modelSet(SpinnerField field, SpinnerModel model) {
		if (log.isDebugEnabled()) {
			log.debug(SPINNER_MODEL_SET, model, field);
		} else {
			log.debug(SPINNER_MODEL_SET_MESSAGE, model, field.getName());
		}
	}
}
