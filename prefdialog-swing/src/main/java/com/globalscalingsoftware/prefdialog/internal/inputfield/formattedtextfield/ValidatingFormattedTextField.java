package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.text.ParseException;

import javax.swing.JFormattedTextField;

public class ValidatingFormattedTextField extends
		ValidatingTextField<JFormattedTextField> {

	public ValidatingFormattedTextField(JFormattedTextField field) {
		super(field);
	}

	@Override
	protected void setValue(Object value) {
		super.setValue(value);
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
