/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.spreadsheet.table;

import javax.swing.table.TableModel;

/**
 * Extends the table model for undo functionality.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface SpreadsheetModel extends TableModel {

	/**
	 * Undo the last set value. Other then {@code setValue()} this method will
	 * not inform the undo manager of the change.
	 * 
	 * @param value
	 *            the value of the cell.
	 * 
	 * @param row
	 *            the row of the cell.
	 * 
	 * @param column
	 *            the column of the cell.
	 */
	void undoValue(Object value, int row, int column);
}
