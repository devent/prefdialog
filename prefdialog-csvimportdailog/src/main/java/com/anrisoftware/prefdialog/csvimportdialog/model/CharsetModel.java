package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.nio.charset.Charset;

import javax.swing.DefaultComboBoxModel;

import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ItemDefault;

@SuppressWarnings("serial")
public class CharsetModel extends DefaultComboBoxModel<Object> {

	@Override
	public void insertElementAt(Object item, int index) {
		if (item instanceof ItemDefault) {
			super.insertElementAt(item, index);
		} else if (item instanceof Charset) {
			super.insertElementAt(item, index);
		}
	}
}
