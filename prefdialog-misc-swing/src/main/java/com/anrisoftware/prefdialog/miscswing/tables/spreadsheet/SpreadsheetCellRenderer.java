package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 * Not show selection on not editable cells.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class SpreadsheetCellRenderer extends DefaultTableCellRenderer {

	private Color unselectedForeground;

	private Color unselectedBackground;

	private Color uneditableBackground;

	private Color uneditableForeground;

	public SpreadsheetCellRenderer() {
		this.uneditableForeground = null;
		this.uneditableBackground = null;
	}

	public void setUneditableBackground(Color c) {
		this.uneditableBackground = c;
	}

	@Override
	public void setForeground(Color c) {
		super.setForeground(c);
		this.unselectedForeground = c;
	}

	@Override
	public void setBackground(Color c) {
		super.setBackground(c);
		this.unselectedBackground = c;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		if (uneditableBackground == null) {
			uneditableBackground = table.getTableHeader().getBackground();
		}
		if (uneditableForeground == null) {
			uneditableForeground = table.getTableHeader().getForeground();
		}
		TableModel model = table.getModel();
		boolean editable = model.isCellEditable(row, column);
		if (!editable && !isSelected) {
			super.setBackground(uneditableBackground);
		} else if (!editable && isSelected) {
			super.setForeground(unselectedForeground);
			if (uneditableBackground == null) {
				super.setBackground(unselectedBackground);
			} else {
				super.setBackground(uneditableBackground);
			}
		} else if (editable && isSelected) {
			super.setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else if (editable && !isSelected) {
			super.setForeground(unselectedForeground);
			super.setBackground(unselectedBackground);
		}
		return this;
	}

}
