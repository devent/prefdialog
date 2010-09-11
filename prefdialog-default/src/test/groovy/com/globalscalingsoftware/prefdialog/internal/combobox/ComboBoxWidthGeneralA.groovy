package com.globalscalingsoftware.prefdialog.internal.combobox

import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements 
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements 
import java.util.List;
class ComboBoxWidthGeneralA {
	
	@ComboBoxElements("combobox1")
	List<String> comboBoxElements = ["first element", "second element", "third element"]
	
	@ComboBox(value="combobox1", width=1.0)
	String comboBox
	
	@Override
	public String toString() {
		"General"
	}
}

