package com.globalscalingsoftware.prefdialog.internal;

import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField;

class ValidatingFormattedTextField extends ValidatingTextField {

	public ValidatingFormattedTextField(JFormattedTextField textField) {
		super(textField);
	}

	public JFormattedTextField getFormattedTextField() {
		return (JFormattedTextField) getTextField();
	}

	@Override
	public void focusLost(FocusEvent ev) {
		try {
			getFormattedTextField().commitEdit();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
