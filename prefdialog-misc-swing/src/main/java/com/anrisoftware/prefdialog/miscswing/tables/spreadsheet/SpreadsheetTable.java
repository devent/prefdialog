package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import javax.inject.Inject;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.google.inject.assistedinject.Assisted;

public class SpreadsheetTable {

	private final JTable table;

	private final SpreadsheetTableModel model;

	@Inject
	SpreadsheetTable(SpreadsheetTableModelFactory modelFactory,
			@Assisted JTable table, @Assisted SpreadsheetModel model,
			@Assisted ViewRange range) {
		this.table = table;
		this.model = modelFactory.create(model, range);
		setupTable();
	}

	private void setupTable() {
		table.setModel(model);
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
