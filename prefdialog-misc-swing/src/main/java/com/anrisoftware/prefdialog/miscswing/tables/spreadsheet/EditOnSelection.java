package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Starts cell editing on selection.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class EditOnSelection implements ListSelectionListener {

	private final JTable table;

	private int row;

	private int column;

	private JTextField field;

	public EditOnSelection(JTable table) {
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			return;
		}
		editCell();
	}

	private void editCell() {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				editCellInAWT();
			}

		});
	}

	private void editCellInAWT() {
		row = table.getSelectedRow();
		column = table.getSelectedColumn();
		if (table.editCellAt(row, column)) {
			Component editor = table.getEditorComponent();
			editor.requestFocusInWindow();
			if (editor instanceof JTextField && field == null) {
				addMoveCellAction((JTextField) editor);
			}
		}
	}

	private void addMoveCellAction(JTextField field) {
		this.field = field;
		field.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveCell();
			}
		});
	}

	private void moveCell() {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				moveCellInAWT();
			}

		});
	}

	private void moveCellInAWT() {
		row = row + 1;
		table.getSelectionModel().setSelectionInterval(row, row);
		table.getColumnModel().getSelectionModel()
				.setSelectionInterval(column, column);
		table.scrollRectToVisible(table.getCellRect(row, 0, true));
	}
}
