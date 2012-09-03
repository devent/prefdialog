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
package com.anrisoftware.prefdialog.fields.checkbox;

import static org.apache.commons.lang3.Validate.isInstanceOf;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link CheckboxField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class CheckboxFieldLogger extends AbstractLogger {

	/**
	 * Creates logger for {@link CheckboxField}.
	 */
	CheckboxFieldLogger() {
		super(CheckboxField.class);
	}

	void checkValue(CheckboxField field, Object value) {
		isInstanceOf(
				Boolean.class,
				value,
				"The value %s is not a boolean value for the check-box field %s.",
				value, field);
	}

	void textSet(CheckboxField field, String text) {
		log.trace("Set the text ``{}'' to the check box field {}.", text, field);
	}
}
