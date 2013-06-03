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
package com.anrisoftware.prefdialog.fields.formattedtextfield

import java.beans.PropertyVetoException

import com.anrisoftware.prefdialog.annotations.FieldComponent
import com.anrisoftware.prefdialog.annotations.FormattedTextField

/**
 * Bean with text fields.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FormattedTextFieldBean {

	static final NULL_VALUE = "nullStringValue"

	static final INITIAL_VALUE = "initialStringValue"

	static final NOT_EDITABLE = "notEditable"

	static final VALIDATED = "validated"

	static final VALIDATED_VALID_VALUE = "valid"

	static final VALIDATED_INVALID_VALUE = "not valid"

	@FieldComponent
	@FormattedTextField
	public String nullStringValue

	@FieldComponent
	@FormattedTextField
	public int intValue

	@FieldComponent
	@FormattedTextField
	public int doubleValue

	@FieldComponent
	@FormattedTextField
	public String initialStringValue = "Text"

	@FieldComponent
	@FormattedTextField(editable = false)
	public String notEditable = "Not Editable"

	private String validated

	public void setValidated(String text) {
		if (text != VALIDATED_VALID_VALUE) {
			throw new PropertyVetoException("Value '$text' not valid.", null)
		}
		this.validated = text
	}

	@FieldComponent
	@FormattedTextField
	public String getValidated() {
		return validated
	}
}

