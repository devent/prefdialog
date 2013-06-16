package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

import javax.swing.JTable;

/**
 * Factory to create a spreadsheet like table.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface SpreadsheetTableFactory {

	/**
	 * Decorates the table to be a spreadsheet like table.
	 * 
	 * @param table
	 *            the {@link JTable}.
	 * 
	 * @param model
	 *            the {@link SpreadsheetModel}.
	 * 
	 * @param range
	 *            the {@link ViewRange}.
	 * 
	 * @return the {@link SpreadsheetTable}.
	 */
	SpreadsheetTable create(JTable table, SpreadsheetModel model,
			ViewRange range);

	/**
	 * @see #create(JTable, SpreadsheetModel, ViewRange)
	 */
	SpreadsheetTable create(JTable table, SpreadsheetModel model);
}
