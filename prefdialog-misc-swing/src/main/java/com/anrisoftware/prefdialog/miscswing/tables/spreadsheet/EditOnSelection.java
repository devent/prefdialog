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

	private int row1;

	private int column1;

	private JTextField field;

	private int row0;

	private int column0;

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
		row0 = table.getSelectionModel().getAnchorSelectionIndex();
		row1 = table.getSelectionModel().getLeadSelectionIndex();
		column0 = table.getColumnModel().getSelectionModel()
				.getAnchorSelectionIndex();
		column1 = table.getColumnModel().getSelectionModel()
				.getLeadSelectionIndex();
		if (row0 != row1) {
			return;
		}
		if (column0 != column1) {
			return;
		}
		invokeLater(new Runnable() {

			@Override
			public void run() {
				editCellInAWT();
			}

		});
	}

	private void editCellInAWT() {
		if (table.editCellAt(row1, column1)) {
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
		row1 = row1 + 1;
		table.getSelectionModel().setSelectionInterval(row1, row1);
		table.getColumnModel().getSelectionModel()
				.setSelectionInterval(column1, column1);
		table.scrollRectToVisible(table.getCellRect(row1, 0, true));
	}
}
