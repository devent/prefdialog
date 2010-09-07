package com.globalscalingsoftware.prefdialog.internal.formattedtextfield;

import java.text.ParseException;

import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.IValidator;

public class ValidatingFormattedTextField extends
		ValidatingTextField<JFormattedTextField> {

	public ValidatingFormattedTextField(Object value,
			@SuppressWarnings("rawtypes") IValidator validator,
			JFormattedTextField field) {
		super(value, validator, field);
	}

	@Override
	protected void setValue(Object value) {
		getField().setValue(value);
	}

	@Override
	protected Object getValue() {
		return getField().getValue();
	}

	@Override
	protected boolean isEditValid() {
		return getField().isEditValid();
	}

	@Override
	protected void commitEdit() throws ParseException {
		getField().commitEdit();
	}

}
