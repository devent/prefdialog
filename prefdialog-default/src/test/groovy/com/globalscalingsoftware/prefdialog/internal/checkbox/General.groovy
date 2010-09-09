package com.globalscalingsoftware.prefdialog.internal.checkbox


import com.globalscalingsoftware.prefdialog.annotations.Checkbox;

class General {
	
	@Checkbox
	boolean automaticSave = false
	
	@Override
	public String toString() {
		"General"
	}
}
