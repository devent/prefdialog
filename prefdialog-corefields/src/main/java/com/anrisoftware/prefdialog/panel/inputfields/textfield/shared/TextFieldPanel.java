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
package com.anrisoftware.prefdialog.panel.inputfields.textfield.shared;

import static java.lang.String.format;

import javax.swing.JTextField;

import org.apache.commons.lang.WordUtils;

import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldPanel;
import com.anrisoftware.prefdialog.validators.Validator;

/**
 * Will show a tool-tip text from the validator and decorate the
 * {@link JTextField} if the input was not valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class TextFieldPanel extends AbstractLabelFieldPanel<JTextField> {

	private final ValidatingTextField<?> textField;

	private String fieldTitle;

	private boolean inputValid;

	/**
	 * Sets the text field.
	 * 
	 * @param textField
	 *            the {@link ValidatingTextField}.
	 */
	public TextFieldPanel(ValidatingTextField<?> textField) {
		super(textField.getField());
		this.textField = textField;
		this.inputValid = true;
	}

	@Override
	public void setTitle(String title) {
		this.fieldTitle = title;
		super.setTitle(title);
	}

	/**
	 * Sets the text of the validator. The text will be shown in a tool-tip.
	 * 
	 * @param validatorText
	 *            the validator text or <code>null</code> if no text should be
	 *            set.
	 */
	public void setValidatorText(String validatorText) {
		if (validatorText == null) {
			setToolTipText(null);
			setShowToolTip(false);
		} else {
			String text = formatValidatorText(validatorText);
			setToolTipText(text);
			setShowToolTip(true);
		}
	}

	private String formatValidatorText(String validatorText) {
		validatorText = WordUtils.wrap(validatorText, 42);
		validatorText = validatorText.replace("\n", "<br/>");
		return format("<html><strong>%s</strong> - %s</html>", fieldTitle,
				validatorText);
	}

	@Override
	public Object getValue() {
		Object value = textField.getValue();
		return value;
	}

	@Override
	public void setValue(Object value) {
		textField.setValue(value);
	}

	/**
	 * Add the {@link ValidListener} to the text field.
	 */
	public void addValidListener(ValidListener l) {
		textField.addValidListener(l);
	}

	/**
	 * Sets the {@link Validator} to the text field.
	 */
	public void setValidator(Validator<?> validator) {
		textField.setValidator(validator);
	}

	/**
	 * Sets if the current input is valid.
	 */
	public void setInputValid(boolean inputValid) {
		this.inputValid = inputValid;
	}

	@Override
	public boolean isInputValid() {
		return inputValid;
	}

}
