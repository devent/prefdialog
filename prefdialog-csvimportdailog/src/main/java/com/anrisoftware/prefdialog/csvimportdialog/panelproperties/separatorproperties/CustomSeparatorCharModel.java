package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.separatorproperties;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class CustomSeparatorCharModel extends DefaultComboBoxModel<Object> {

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
