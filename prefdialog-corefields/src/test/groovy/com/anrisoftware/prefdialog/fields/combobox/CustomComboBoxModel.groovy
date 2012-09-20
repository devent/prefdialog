package com.anrisoftware.prefdialog.fields.combobox

import javax.swing.DefaultComboBoxModel

/**
 * Custom combo box model with a public standard constructor.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class CustomComboBoxModel extends DefaultComboBoxModel {

	CustomComboBoxModel() {
		super(["Eins", "Zwei", "Drei"])
	}

}
