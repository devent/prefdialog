/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.textfield

import java.beans.PropertyVetoException

import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.TextField

/**
 * Bean with text fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class TextFieldBean {

	static final NULL_VALUE = "nullValue"

	static final INITIAL_VALUE = "initialValue"

	static final NOT_EDITABLE = "notEditable"

	static final VALIDATED = "validated"

	static final VALIDATED_VALID_VALUE = "valid"

	static final VALIDATED_INVALID_VALUE = "not valid"

	@FieldComponent
	@TextField
	public String nullValue

	@FieldComponent
	@TextField
	public String initialValue = "Text"

	@FieldComponent
	@TextField(editable = false)
	public String notEditable = "Not Editable"

	private String validated = VALIDATED_VALID_VALUE

	public void setValidated(String text) {
		if (text != VALIDATED_VALID_VALUE) {
			throw new PropertyVetoException("Value '$text' not valid.", null)
		}
		this.validated = text
	}

	@FieldComponent
	@TextField
	public String getValidated() {
		return validated
	}
}

