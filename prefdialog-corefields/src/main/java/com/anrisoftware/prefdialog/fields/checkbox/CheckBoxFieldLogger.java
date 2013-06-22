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
 * Logging messages for {@link CheckBoxField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CheckBoxFieldLogger extends AbstractLogger {

	private static final String VALUE_NOT_BOOLEAN = "Value '%s' is not boolean value for %s.";

	/**
	 * Creates logger for {@link CheckBoxField}.
	 */
	CheckBoxFieldLogger() {
		super(CheckBoxField.class);
	}

	void checkValue(CheckBoxField field, Object value) {
		isInstanceOf(Boolean.class, value, VALUE_NOT_BOOLEAN, value, field);
	}
}
