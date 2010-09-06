package com.globalscalingsoftware.prefdialog.internal
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;

import com.globalscalingsoftware.prefdialog.*;

class General {
	
	@FormattedTextField
	int fields = 4
	
	@Override
	public String toString() {
		"General"
	}
}
