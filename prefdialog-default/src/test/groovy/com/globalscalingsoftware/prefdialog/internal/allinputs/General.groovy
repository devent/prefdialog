package com.globalscalingsoftware.prefdialog.internal.allinputs


import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.HelpText 
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.annotations.Validated 
import com.globalscalingsoftware.prefdialog.internal.FieldsValidator 

class General {
	
	@TextField
	@HelpText("Must not be empty")
	@Validated(NotEmptyStringValidator)
	String name = ""
	
	@FormattedTextField
	@HelpText("Must be a number and between 2 and 100")
	@Validated(FieldsValidator)
	int fields = 4
	
	@Checkbox
	boolean automaticSave = false
	
	@Override
	public String toString() {
		"General"
	}
}
