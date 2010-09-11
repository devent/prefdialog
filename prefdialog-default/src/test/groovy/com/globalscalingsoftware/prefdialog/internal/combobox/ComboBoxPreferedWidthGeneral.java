package com.globalscalingsoftware.prefdialog.internal.combobox;

import java.util.Arrays;
import java.util.List;

import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements;

public class ComboBoxPreferedWidthGeneral {

	@ComboBoxElements("combobox1")
	List<String> comboBoxElements = Arrays.asList(new String[] {
			"first element", "second element", "third element" });

	@ComboBox(value = "combobox1", width = -2.0)
	String comboBox;

	public List<String> getComboBoxElements() {
		return comboBoxElements;
	}

	public String getComboBox() {
		return comboBox;
	}

	public void setComboBox(String comboBox) {
		this.comboBox = comboBox;
	}

	@Override
	public String toString() {
		return "General";
	}
}
