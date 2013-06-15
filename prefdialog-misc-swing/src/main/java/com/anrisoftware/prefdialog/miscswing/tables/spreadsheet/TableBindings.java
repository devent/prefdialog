package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_TAB;
import static javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

/**
 * Rebinds keys of the spreadsheet table.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
class TableBindings {

	public void bindTable(JTable table) {
		setupSelectNextEditableCell(table);
		InputMap inputs = table.getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap actions = table.getActionMap();
		setupEnter(table, inputs, actions);
		setupTab(table, inputs, actions);
	}

	private void setupSelectNextEditableCell(final JTable table) {
		table.getColumnModel().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						int rowIndex = table.getSelectedRow();
						int index = table.getSelectedColumn();
						TableModel model = table.getModel();
						while (!model.isCellEditable(rowIndex, index)) {
							index++;
						}
						table.setColumnSelectionInterval(index, index);
					}
				});
	}

	private void setupTab(final JTable table, InputMap inputs, ActionMap actions) {
		inputs.put(KeyStroke.getKeyStroke(VK_TAB, 0), "Tab");
		actions.put("Tab", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				TableModel model = table.getModel();
				int maxcolumns = model.getColumnCount();
				int column = table.getSelectedColumn() + 1;
				if (column < maxcolumns) {
					table.setColumnSelectionInterval(column, column);
				} else {
					table.setColumnSelectionInterval(0, 0);
				}
			}
		});
	}

	private void setupEnter(final JTable table, InputMap inputs,
			ActionMap actions) {
		inputs.put(KeyStroke.getKeyStroke(VK_ENTER, 0), "Enter");
		actions.put("Enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				int maxrows = table.getModel().getRowCount();
				int row = table.getSelectedRow() + 1;
				if (row < maxrows) {
					table.setRowSelectionInterval(row, row);
					table.scrollRectToVisible(table.getCellRect(row, 0, true));
				}
			}
		});
	}
}
