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

import static java.lang.String.format;

import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractLabelFieldPanel;
import com.globalscalingsoftware.prefdialog.validators.Validator;

class TextFieldPanel extends AbstractLabelFieldPanel<JTextField> {

	private final ValidatingTextField<?> textField;

	private String fieldTitle;

	public TextFieldPanel(ValidatingTextField<?> textField) {
		super(textField.getField());
		this.textField = textField;
	}

	@Override
	public void setTitle(String title) {
		this.fieldTitle = title;
		super.setTitle(title);
	}

	public void setValidatorText(String validatorText) {
		String text = format("%s (%s): ", fieldTitle, validatorText);
		setLabelText(text);
	}

	public void clearValidatorText() {
		String text = format("%s: ", fieldTitle);
		setLabelText(text);
	}

	@Override
	public Object getValue() {
		return textField.getValue();
	}

	@Override
	public void setValue(Object value) {
		textField.setValue(value);
	}

	public void addValidListener(ValidListener l) {
		textField.addValidListener(l);
	}

	public void setValidator(Validator<?> validator) {
		textField.setValidator(validator);
	}

}
