package com.globalscalingsoftware.prefdialog.panel.inputfields.fontchooser.fontcombobox;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
class FontComboBoxListCellRenderer extends JComponent implements
		ListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (value instanceof FontsModelItem) {
			FontsModelItem item = (FontsModelItem) value;
			item.setSelected(list, isSelected);
			return item.getPanel();
		}
		return this;
	}

}
