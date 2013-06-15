package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import javax.inject.Inject;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import com.google.inject.assistedinject.Assisted;

public class SpreadsheetTable {

	private final JTable table;

	private final SpreadsheetTableModel model;

	private ListSelectionListener selectionListener;

	@Inject
	SpreadsheetTable(SpreadsheetTableModelFactory modelFactory,
			@Assisted JTable table, @Assisted SpreadsheetModel model,
			@Assisted ViewRange range) {
		this.table = table;
		this.model = modelFactory.create(model, range);
		this.selectionListener = new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				updateOffset();
			}
		};
		setupTable();
	}

	private void updateOffset() {
		int[] selected = table.getSelectedRows();
		int maxRow = table.getSelectionModel().getMaxSelectionIndex();
		int maximum = getMaximum();
		int offset = getOffset();
		if (maxRow == 0) {
			if (offset > 0) {
				setOffset(offset - 1);
				for (int row : selected) {
					table.addRowSelectionInterval(row + 1, row + 1);
				}
			}
		} else if (maxRow >= maximum - 1) {
			int difference = maximum - maxRow + offset;
			setOffset(difference);
			for (int row : selected) {
				table.addRowSelectionInterval(row - 1, row - 1);
			}
		}
	}

	private void setupTable() {
		table.setModel(model);
		table.getSelectionModel().addListSelectionListener(selectionListener);
	}

	public JTable getTable() {
		return table;
	}

	public TableModel getModel() {
		return model;
	}

	public void setViewRange(ViewRange range) {
		model.setViewRange(range);
	}

	public ViewRange getViewRange() {
		return model.getViewRange();
	}

	public void setOffset(int offset) {
		model.getViewRange().setOffset(offset);
	}

	public int getOffset() {
		return model.getOffset();
	}

	public void setMaximum(int maximum) {
		model.getViewRange().setMaximum(maximum);
	}

	public int getMaximum() {
		return model.getMaximum();
	}

}
