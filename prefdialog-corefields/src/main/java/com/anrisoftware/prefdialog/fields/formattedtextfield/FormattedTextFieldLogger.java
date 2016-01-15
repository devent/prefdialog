/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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
package com.anrisoftware.prefdialog.fields.formattedtextfield;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link FormattedTextField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FormattedTextFieldLogger extends AbstractLogger {

	private static final String SET_EDITABLE = "Set editable to {} for {}.";

	/**
	 * Creates logger for {@link FormattedTextField}.
	 */
	FormattedTextFieldLogger() {
		super(FormattedTextField.class);
	}

	void editableSet(FormattedTextField field, boolean editable) {
		log.debug(SET_EDITABLE, editable, field);
	}
}
