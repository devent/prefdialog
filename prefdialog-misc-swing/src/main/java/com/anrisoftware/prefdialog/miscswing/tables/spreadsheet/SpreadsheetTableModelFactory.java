package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet;

/**
 * Factory to create a spreadsheet model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface SpreadsheetTableModelFactory {

	/**
	 * Creates the spreadsheet model with the specified model as the underlying
	 * model.
	 * 
	 * @param model
	 *            the underlying {@link SpreadsheetModel}.
	 * 
	 * @param range
	 *            the {@link ViewRange}.
	 * 
	 * @return the {@link SpreadsheetTableModel}.
	 */
	SpreadsheetTableModel create(SpreadsheetModel model, ViewRange range);
}
