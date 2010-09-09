package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;


import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IValidator;

public class FormattedTextFieldInputField extends AbstractTextField implements
		IInputField {

	public FormattedTextFieldInputField(Object value, String fieldName,
			String helpText, IValidator<?> validator) {
		super(fieldName, helpText, new ValidatingFormattedTextField(value,
				validator, new JFormattedTextField()));
	}

}
