package com.globalscalingsoftware.prefdialog.internal.textfield

import inputfields.TextField;

import com.globalscalingsoftware.prefdialog.annotations.HelpText;
import com.globalscalingsoftware.prefdialog.annotations.InputField;
import com.globalscalingsoftware.prefdialog.annotations.Validated;

class General {
	
	@InputField(TextField)
	@HelpText("Must not be empty")
	@Validated(NotEmptyStringValidator)
	String name = ""
	
	@Override
	public String toString() {
		"General"
	}
}
