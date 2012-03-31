package com.anrisoftware.prefdialog.panel;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

@SuppressWarnings("serial")
public class LookAndFeelsRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		if (value instanceof LookAndFeelInfoListItem) {
			LookAndFeelInfoListItem item = (LookAndFeelInfoListItem) value;
			setupComponent(item);
		}
		return this;
	}

	private void setupComponent(LookAndFeelInfoListItem item) {
		setText(item.getLookAndFeelInfo().getName());
	}
}
