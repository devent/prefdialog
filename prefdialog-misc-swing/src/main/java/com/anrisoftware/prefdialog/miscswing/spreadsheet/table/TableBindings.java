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

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_TAB;
import static javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Re-binds keys of the spreadsheet table.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class TableBindings {

	private final ListSelectionListener nextEditableCellListener;

	private final Action tabAction;

	private final Action enterAction;

	private TableColumnModel columnModel;

	private JTable table;

	private InputMap inputs;

	private ActionMap actions;

	private ListSelectionModel columnSelectionModel;

	private TableModel model;

	TableBindings() {
		this.enterAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int maxrows = model.getRowCount();
				int row = table.getSelectedRow() + 1;
				if (row < maxrows) {
					table.setRowSelectionInterval(row, row);
					table.scrollRectToVisible(table.getCellRect(row, 0, true));
				}
			}
		};
		this.tabAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int maxcolumns = model.getColumnCount();
				int column = table.getSelectedColumn() + 1;
				if (column < maxcolumns) {
					table.setColumnSelectionInterval(column, column);
				} else {
					table.setColumnSelectionInterval(0, 0);
				}
			}
		};
		this.nextEditableCellListener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int rowIndex = table.getSelectedRow();
				int index = table.getSelectedColumn();
				if (rowIndex < 0 || index < 0) {
					return;
				}
				while (index < model.getColumnCount()
						&& !model.isCellEditable(rowIndex, index)) {
					index++;
				}
				if (index < model.getColumnCount()) {
					table.setColumnSelectionInterval(index, index);
				}
			}
		};
	}

	/**
	 * Sets the bindings for the specified table.
	 * 
	 * @param table
	 *            the {@link JTable}.
	 */
	public void setTable(JTable table) {
		this.table = table;
		this.model = table.getModel();
		this.columnModel = table.getColumnModel();
		this.columnSelectionModel = columnModel.getSelectionModel();
		this.inputs = table.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		this.actions = table.getActionMap();
		setupSelectNextEditableCell();
		setupEnter();
		setupTab();
	}

	private void setupSelectNextEditableCell() {
		if (columnSelectionModel != null) {
			columnSelectionModel
					.removeListSelectionListener(nextEditableCellListener);
		}
		columnSelectionModel.addListSelectionListener(nextEditableCellListener);
	}

	private void setupTab() {
		inputs.put(KeyStroke.getKeyStroke(VK_TAB, 0), "Tab");
		actions.put("Tab", tabAction);
	}

	private void setupEnter() {
		inputs.put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
		actions.put("Enter", enterAction);
	}
}
