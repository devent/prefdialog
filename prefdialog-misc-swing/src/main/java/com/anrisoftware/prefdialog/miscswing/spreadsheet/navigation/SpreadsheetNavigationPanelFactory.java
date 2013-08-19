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
package com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.anrisoftware.prefdialog.miscswing.spreadsheet.table.SpreadsheetTable;

/**
 * Factory to create a new spreadsheet table navigation panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
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
