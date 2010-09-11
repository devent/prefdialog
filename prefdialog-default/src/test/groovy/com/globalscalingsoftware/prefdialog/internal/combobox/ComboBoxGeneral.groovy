package com.globalscalingsoftware.prefdialog.internal.combobox

import com.globalscalingsoftware.prefdialog.annotations.ComboBox 
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements 
import java.util.List;

class ComboBoxGeneral {
	
	@ComboBoxElements("combobox1")
	List<String> comboBoxElements = ["first element", "second element", "third element"]
	
	@ComboBox("combobox1")
	String comboBox
	
	@Override
	public String toString() {
		"General"
	}
}

