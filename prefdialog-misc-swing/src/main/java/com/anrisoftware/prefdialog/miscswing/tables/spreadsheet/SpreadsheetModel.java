package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import javax.swing.table.TableModel;

public interface SpreadsheetModel extends TableModel {

	/**
	 * Tests if the rows with the specified column index are editable.
	 * 
	 * @param columnIndex
	 *            the column index.
	 * 
	 * @return {@code true} if the rows are editable.
	 */
	boolean isColumnEditable(int columnIndex);

	/**
	 * Adds the specified count of rows to the model end.
	 * 
	 * @param rows
	 *            the rows count to add.
	 */
	void addRows(int rows);

	/**
	 * Returns the default value for the column with the specified index.
	 * 
	 * @param rowIndex
	 *            the row index.
	 * 
	 * @param columnIndex
	 *            the column index.
	 * 
	 * @return the default value for the column or {@code null}.
	 */
	Object getColumnValue(int rowIndex, int columnIndex);

}
