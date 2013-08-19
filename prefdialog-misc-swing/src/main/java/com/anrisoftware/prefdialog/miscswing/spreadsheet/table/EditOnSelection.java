/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-misc-swing.
 * 
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.spreadsheet.table;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

/**
 * Starts cell editing on selection.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class EditOnSelection implements ListSelectionListener {

	private final JTable table;

	private final ListSelectionModel model;

	private final TableColumnModel columnModel;

	private JTextField field;

	private int row1;

	private int row0;

	private int column0;

	private int column1;

	public EditOnSelection(JTable table) {
		this.table = table;
		this.model = table.getSelectionModel();
		this.columnModel = table.getColumnModel();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			return;
		}
		editCell();
	}

	private void editCell() {
		row0 = model.getAnchorSelectionIndex();
		row1 = model.getLeadSelectionIndex();
		column0 = columnModel.getSelectionModel().getAnchorSelectionIndex();
		column1 = columnModel.getSelectionModel().getLeadSelectionIndex();
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
		model.setSelectionInterval(row1, row1);
		columnModel.getSelectionModel().setSelectionInterval(column1, column1);
		table.scrollRectToVisible(table.getCellRect(row1, 0, true));
	}
}
