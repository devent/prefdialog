package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet

import javax.swing.table.AbstractTableModel

class NumbersModel extends AbstractTableModel {

	private final data

	private final int columns

	private final int rows

	NumbersModel(int columns, int rows) {
		this.data = generateRows(columns, rows)
		this.columns = columns + 1
		this.rows = rows
	}

	private generateRows(int columns, int rows) {
		def data = []
		def indexrow = []
		data << indexrow
		(1..rows).each { int r -> indexrow << r }
		(1..columns).each { int c ->
			def collist = []
			data << collist
			(1..rows).each { int r ->
				collist << (r + 1) * c
			}
		}
		data
	}

	@Override
	public int getRowCount() {
		return rows;
	}

	@Override
	public int getColumnCount() {
		return columns;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[columnIndex][rowIndex];
	}
}
