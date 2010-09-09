package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;


import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IValidator;

public class TextFieldInputField extends AbstractTextField implements
		IInputField {

	public TextFieldInputField(Object value, String fieldName, String helpText,
			IValidator<?> validator) {
		super(fieldName, helpText, new ValidatingTextField<JTextField>(value,
				validator, new JTextField()));
	}

}
