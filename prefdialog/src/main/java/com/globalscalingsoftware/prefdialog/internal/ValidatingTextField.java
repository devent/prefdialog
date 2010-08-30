package com.globalscalingsoftware.prefdialog.internal;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

class ValidatingTextField implements FocusListener {

	private final JTextField textField;

	public ValidatingTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField() {
		return textField;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

}
