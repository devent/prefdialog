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
package com.anrisoftware.prefdialog.fields.child;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.fields.FieldComponent;

/**
 * Logging messages for {@link ChildField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class ChildFieldLogger extends AbstractLogger {

	private static final String ADD_CHILD_FIELD = "Add child field {} to {}.";

	/**
	 * Creates logger for {@link ChildField}.
	 */
	ChildFieldLogger() {
		super(ChildField.class);
	}

	void addChildField(ChildField field, FieldComponent<?> f) {
		log.debug(ADD_CHILD_FIELD, f, field);
	}
}
