package com.globalscalingsoftware.prefdialog.internal.formattedtextfield;

import inputfields.TextField;

import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.IValidator;

public class TextFieldImpl extends AbstractTextField implements
		TextField {

	public TextFieldImpl(Object value, String fieldName,
			String helpText, IValidator<?> validator) {
		super(fieldName, helpText, new ValidatingTextField<JTextField>(value,
				validator, new JTextField()));
	}

}
