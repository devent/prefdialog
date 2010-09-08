package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import inputfields.TextField;

import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.IValidator;

public class TextFieldInputField extends AbstractTextField implements TextField {

	public TextFieldInputField(Object value, String fieldName, String helpText,
			IValidator<?> validator) {
		super(fieldName, helpText, new ValidatingTextField<JTextField>(value,
				validator, new JTextField()));
	}

}
