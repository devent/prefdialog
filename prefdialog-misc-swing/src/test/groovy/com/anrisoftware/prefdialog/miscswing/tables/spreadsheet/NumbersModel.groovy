/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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
package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet

import javax.swing.table.AbstractTableModel

/**
 * Table model that generated a numbers table.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class NumbersModel extends AbstractTableModel implements SpreadsheetModel {

	private static final String[] COLUMN_NAMES = ["Index"]

	private final data

	private final int columns

	NumbersModel(int columns, int rows) {
		this.data = generateRows(columns, rows)
		this.columns = columns
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
		return data[0].size()
	}

	@Override
	public int getColumnCount() {
		return columns + COLUMN_NAMES.length
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[columnIndex][rowIndex]
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[columnIndex][rowIndex] = aValue
		fireTableCellUpdated(rowIndex, columnIndex)
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if( columnIndex >= COLUMN_NAMES.length) {
			return true
		} else {
			return false
		}
	}

	@Override
	public boolean isColumnEditable(int columnIndex) {
		if( columnIndex >= COLUMN_NAMES.length) {
			return true
		} else {
			return false
		}
	}

	@Override
	public void addRows(int rows) {
		int row = getRowCount()
		(1..rows).each { int r -> data[0] << r + row }
		(1..columns).each { int c ->
			def collist = []
			(1..rows).each { int r -> data[c] << 0 }
		}
	}

	@Override
	public Object getColumnValue(int rowIndex, int columnIndex) {
		if( columnIndex == COLUMN_NAMES.length - 1) {
			return rowIndex + 1
		} else {
			return 0
		}
	}
}
