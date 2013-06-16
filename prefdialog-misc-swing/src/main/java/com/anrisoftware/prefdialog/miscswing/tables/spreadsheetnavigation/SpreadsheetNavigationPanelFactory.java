package com.anrisoftware.prefdialog.miscswing.tables.spreadsheetnavigation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.anrisoftware.prefdialog.miscswing.tables.spreadsheet.SpreadsheetTable;

/**
 * Factory to create a new spreadsheet table navigation panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface SpreadsheetNavigationPanelFactory {

	/**
	 * Creates the navigation panel.
	 * 
	 * @param container
	 *            the {@link JPanel} parent container.
	 * 
	 * @param table
	 *            the {@link SpreadsheetTable}.
	 * 
	 * @param pane
	 *            the {@link JScrollPane} scroll pane for the spreadsheet table.
	 * 
	 * @return the {@link SpreadsheetNavigationPanel}.
	 */
	SpreadsheetNavigationPanel create(JPanel container, SpreadsheetTable table,
			JScrollPane pane);

	/**
	 * @see #create(JPanel, SpreadsheetTable, JScrollPane)
	 */
	SpreadsheetNavigationPanel create(JPanel container, SpreadsheetTable table);

}
