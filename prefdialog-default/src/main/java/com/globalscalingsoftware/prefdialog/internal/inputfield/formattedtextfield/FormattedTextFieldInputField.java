package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.lang.reflect.Field;

import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.InputField;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;

public class FormattedTextFieldInputField extends AbstractTextField implements
		InputField {

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
