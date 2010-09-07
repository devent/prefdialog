package com.globalscalingsoftware.prefdialog.internal
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;

import com.globalscalingsoftware.prefdialog.*;

class General {
	
	@FormattedTextField(helpText="Must be a number and greater then 2")
	int fields = 4
	
	@Override
	public String toString() {
		"General"
	}
}
