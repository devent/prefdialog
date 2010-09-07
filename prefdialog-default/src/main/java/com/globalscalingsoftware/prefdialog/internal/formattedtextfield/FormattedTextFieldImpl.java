package com.globalscalingsoftware.prefdialog.internal.formattedtextfield;

import inputfields.FormattedTextField;

import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.IValidator;

public class FormattedTextFieldImpl extends AbstractTextField implements
		FormattedTextField {

	public FormattedTextFieldImpl(Object value, String fieldName,
			String helpText, IValidator<?> validator) {
		super(fieldName, helpText, new ValidatingFormattedTextField(value,
				validator, new JFormattedTextField()));
	}

}
