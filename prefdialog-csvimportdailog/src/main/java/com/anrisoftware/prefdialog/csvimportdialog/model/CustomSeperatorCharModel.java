package com.anrisoftware.prefdialog.csvimportdialog.model;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class CustomSeperatorCharModel extends DefaultComboBoxModel<Object> {

	@Override
	public void addElement(Object item) {
		if (item.toString().charAt(0) != '\0') {
			super.addElement(item);
		}
	}

	@Override
	public void insertElementAt(Object item, int index) {
		if (item.toString().charAt(0) != '\0') {
			super.insertElementAt(item, index);
		}
	}
}
