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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield;

import static java.lang.String.format;

import javax.swing.JTextField;

import org.apache.commons.lang.WordUtils;

import com.globalscalingsoftware.prefdialog.InputChangedCallback;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractLabelFieldPanel;
import com.globalscalingsoftware.prefdialog.validators.Validator;

class TextFieldPanel extends AbstractLabelFieldPanel<JTextField> {

	private final ValidatingTextField<?> textField;

	private String fieldTitle;

	private boolean inputValid;

	private boolean ignoreInputChanged;

	private Object oldValue;

	public TextFieldPanel(ValidatingTextField<?> textField) {
		super(textField.getField());
		this.textField = textField;
		this.inputValid = true;
		this.ignoreInputChanged = true;
		this.oldValue = "";
		setup();
	}

	private void setup() {
		this.textField.setInputChangedCallback(new InputChangedCallback() {

			@Override
			public void inputChanged(Object source) {
				TextFieldPanel.this.inputChanged();
			}
		});
	}

	@Override
	public void setTitle(String title) {
		this.fieldTitle = title;
		super.setTitle(title);
	}

	public void setValidatorText(String validatorText) {
		inputValid = false;
		validatorText = WordUtils.wrap(validatorText, 42);
		validatorText = validatorText.replace("\n", "<br/>");
		String text = format("<html><strong>%s</strong> - %s</html>",
				fieldTitle, validatorText);
		setToolTipText(text);
		showToolTip();
	}

	public void clearValidatorText() {
		inputValid = true;
		hideToolTip();
	}

	@Override
	public Object getValue() {
		Object value = textField.getValue();
		oldValue = value;
		return value;
	}

	@Override
	public void setValue(Object value) {
		textField.setValue(value);
		oldValue = value;
		ignoreInputChanged = false;
	}

	public void addValidListener(ValidListener l) {
		textField.addValidListener(l);
	}

	public void setValidator(Validator<?> validator) {
		textField.setValidator(validator);
	}

	@Override
	public boolean isInputValid() {
		return inputValid;
	}

	@Override
	protected void inputChanged() {
		if (!ignoreInputChanged && !oldValue.equals(getValue())) {
			super.inputChanged();
		}
	}
}
