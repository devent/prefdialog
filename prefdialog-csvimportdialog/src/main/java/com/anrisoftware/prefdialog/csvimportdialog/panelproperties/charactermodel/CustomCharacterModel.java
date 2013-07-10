package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.charactermodel;

import javax.swing.DefaultComboBoxModel;

/**
 * Adds the character in the model only if the character is anything but the
 * zero character {@code \0}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class CustomCharacterModel extends DefaultComboBoxModel<Object> {

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
