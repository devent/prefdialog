package com.globalscalingsoftware.prefdialog.internal.formattedtextfield


import inputfields.FormattedTextField;

import com.globalscalingsoftware.prefdialog.annotations.HelpText;
import com.globalscalingsoftware.prefdialog.annotations.InputField;
import com.globalscalingsoftware.prefdialog.annotations.Validated;
import com.globalscalingsoftware.prefdialog.internal.FieldsValidator 

class General {
	
	@InputField(FormattedTextField)
	@HelpText("Must be a number and between 2 and 100")
	@Validated(FieldsValidator)
	int fields = 4
	
	@Override
	public String toString() {
		"General"
	}
}
