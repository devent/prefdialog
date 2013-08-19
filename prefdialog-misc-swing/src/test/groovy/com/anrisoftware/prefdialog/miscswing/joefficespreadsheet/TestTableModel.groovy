package com.anrisoftware.prefdialog.miscswing.joefficespreadsheet

import javax.swing.table.AbstractTableModel

class TestTableModel extends AbstractTableModel {

	def columns = ["A", "B", "C"]

	def columnsClasses = [
		Integer.class,
		Integer.class,
		Integer.class
	]

	def data = [
		[1, 2, 3],
		[10, 20, 30],
		[12, 13, 14]
	]

	@Override
	public int getColumnCount() {
		return columns.size()
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex]
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex]
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class getColumnClass(int columnIndex) {
		return columnsClasses[columnIndex]
	}

	@Override
	public int getRowCount() {
		return data.size()
	}
}
