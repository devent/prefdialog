package com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox;

import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class FontsModel extends DefaultComboBoxModel {

	public FontsModel(String[] fontNames) {
		for (String name : fontNames) {
			addElement(new FontsModelItem(name));
		}
	}

}
