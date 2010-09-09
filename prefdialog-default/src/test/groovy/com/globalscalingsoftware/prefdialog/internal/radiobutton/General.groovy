package com.globalscalingsoftware.prefdialog.internal.radiobutton

import inputfields.RadioButton;

import com.globalscalingsoftware.prefdialog.annotations.InputField;

class General {
	
	@InputField(RadioButton)
	Colors colors = Colors.BLACK
	
	@Override
	public String toString() {
		"General"
	}
}
