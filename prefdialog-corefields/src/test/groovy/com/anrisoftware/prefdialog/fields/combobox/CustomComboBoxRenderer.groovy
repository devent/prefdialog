package com.anrisoftware.prefdialog.fields.combobox

import java.awt.Component

import javax.swing.DefaultListCellRenderer
import javax.swing.JList

/**
 * Custom combo box renderer with a public standard constructor.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class CustomComboBoxRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
	int index, boolean isSelected, boolean cellHasFocus) {
		value = value.toUpperCase()
		super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus)
		return this
	}
}
