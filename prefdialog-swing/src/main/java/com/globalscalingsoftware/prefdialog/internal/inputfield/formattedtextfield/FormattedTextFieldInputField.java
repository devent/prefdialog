/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.lang.reflect.Field;

import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.annotations.fields.FormattedTextField;

public class FormattedTextFieldInputField extends AbstractTextField {

	public FormattedTextFieldInputField(Object parentObject, Object value,
			Field field) {
		super(parentObject, value, field, FormattedTextField.class,
				new ValidatingFormattedTextField(new JFormattedTextField()));
	}

	@Override
	protected String getValidatorText() {
		String text = super.getValidatorText();
		if (isTextEmpty(text)) {
			text = getDefaultValidatorText();
		}

		return text;
	}

	private String getDefaultValidatorText() {
		return ValidatorTexts.getDefaultValidatorText(getField().getType());
	}

	private boolean isTextEmpty(String text) {
		return text.isEmpty();
	}
}
