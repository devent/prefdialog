package com.globalscalingsoftware.prefdialog.internal.allinputs


import java.util.List;

import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.ComboBox 
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements 
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.HelpText 
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.annotations.Validated 
import com.globalscalingsoftware.prefdialog.internal.FieldsValidator 
import com.globalscalingsoftware.prefdialog.internal.radiobutton.Colors;

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
	
	@RadioButton(columns=2)
	Colors colors = Colors.BLACK
	
	@ComboBoxElements("combobox1")
	List<String> comboBoxElements = ["first element", "second element", "third element"]
	
	@ComboBox("combobox1")
	String comboBox
	
	@Override
	public String toString() {
		"General"
	}
}
