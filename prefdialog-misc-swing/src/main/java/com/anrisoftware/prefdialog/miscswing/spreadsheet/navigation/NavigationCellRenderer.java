package com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Remove selected and focus changes.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class NavigationCellRenderer extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		isSelected = false;
		hasFocus = false;
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		setHorizontalAlignment(SwingConstants.TRAILING);
		return this;
	}
}
