package com.globalscalingsoftware.prefdialog.internal.checkbox

import inputfields.Checkbox;

import com.globalscalingsoftware.prefdialog.annotations.InputField;

class General {
	
	@InputField(Checkbox)
	boolean automaticSave = true
	
	@Override
	public String toString() {
		"General"
	}
}
