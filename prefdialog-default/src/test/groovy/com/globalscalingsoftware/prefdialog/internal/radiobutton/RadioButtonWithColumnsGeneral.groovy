package com.globalscalingsoftware.prefdialog.internal.radiobutton

import com.globalscalingsoftware.prefdialog.annotations.RadioButton;

class RadioButtonWithColumnsGeneral {
	
	@RadioButton(columns=2)
	Colors colors = Colors.BLACK
	
	@Override
	public String toString() {
		"General"
	}
}
