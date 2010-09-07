package com.globalscalingsoftware.prefdialog.internal

import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.HelpText;
import com.globalscalingsoftware.prefdialog.annotations.Validated;

class General {
	
	@FormattedTextField
	@HelpText("Must be a number and between 2 and 100")
	@Validated(FieldsValidator.class)
	int fields = 4
	
	@Override
	public String toString() {
		"General"
	}
}
