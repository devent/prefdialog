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
package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import javax.swing.table.TableModel;

/**
 * Extends the table model to get information about columns and rows that are
 * not actually in the model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
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
