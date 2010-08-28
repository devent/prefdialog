package com.globalscalingsoftware.prefdialog.internal


import com.globalscalingsoftware.prefdialog.*;

class General {
	
	@TextField
	@Parsed(parserClass=IntegerParser.class)
	int fields = 4
	
	@Override
	public String toString() {
		"General"
	}
}
