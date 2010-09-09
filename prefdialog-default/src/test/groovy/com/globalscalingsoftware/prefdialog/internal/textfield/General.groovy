package com.globalscalingsoftware.prefdialog.internal.textfield


import com.globalscalingsoftware.prefdialog.annotations.HelpText;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.annotations.Validated;

class General {
	
	@TextField
	@HelpText("Must not be empty")
	@Validated(NotEmptyStringValidator)
	String name = ""
	
	@Override
	public String toString() {
		"General"
	}
}
