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
package com.anrisoftware.prefdialog.miscswing.spreadsheet.table;

import static com.anrisoftware.prefdialog.miscswing.spreadsheet.table.SpreadsheetTableModule.getFactory;

import javax.inject.Inject;
import javax.swing.JTable;

import com.google.inject.assistedinject.Assisted;

/**
 * Spreadsheet like table.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class SpreadsheetTable {

	/**
	 * Decorate the specified table to be a spreadsheet table.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @see SpreadsheetTableFactory#create(JTable)
	 */
	public static SpreadsheetTable decorate(JTable table) {
		return getFactory().create(table);
	}

	private final JTable table;

	private final TableBindings tableBindings;

	private final EditOnSelection editOnSelection;

	private final SpreadsheetCellRenderer renderer;

	/**
	 * @see SpreadsheetTableFactory#create(JTable)
	 */
	@Inject
	SpreadsheetTable(TableBindings tableBindings,
			EditOnSelection editOnSelection, SpreadsheetCellRenderer renderer,
			@Assisted JTable table) {
		this.tableBindings = tableBindings;
		this.editOnSelection = editOnSelection;
		this.renderer = renderer;
		this.table = table;
		setupTable();
	}

	private void setupTable() {
		tableBindings.setTable(table);
		editOnSelection.setTable(table);
		table.setDefaultRenderer(Object.class, renderer);
		tableBindings.setTable(table);
	}

	/**
	 * Returns the decorated table.
	 * 
	 * @return the {@link JTable}.
	 */
	public JTable getTable() {
		return table;
	}

}
