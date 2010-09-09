package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;


import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.IValidator;
import com.globalscalingsoftware.prefdialog.annotations.TextField;

public class TextFieldInputField extends AbstractTextField implements TextField {

	public TextFieldInputField(Object value, String fieldName, String helpText,
			IValidator<?> validator) {
		super(fieldName, helpText, new ValidatingTextField<JTextField>(value,
				validator, new JTextField()));
	}

}
