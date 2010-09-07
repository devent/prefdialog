package com.globalscalingsoftware.prefdialog.internal

import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.Validated;

class General {
	
	@FormattedTextField(helpText="Must be a number and greater then 2")
	@Validated(FieldsValidator.class)
	int fields = 4
	
	@Override
	public String toString() {
		"General"
	}
}
