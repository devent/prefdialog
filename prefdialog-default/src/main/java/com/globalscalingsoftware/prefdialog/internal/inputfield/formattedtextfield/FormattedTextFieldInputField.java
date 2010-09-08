package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import inputfields.FormattedTextField;

import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.IValidator;

public class FormattedTextFieldInputField extends AbstractTextField implements
		FormattedTextField {

	public FormattedTextFieldInputField(Object value, String fieldName,
			String helpText, IValidator<?> validator) {
		super(fieldName, helpText, new ValidatingFormattedTextField(value,
				validator, new JFormattedTextField()));
	}

}
