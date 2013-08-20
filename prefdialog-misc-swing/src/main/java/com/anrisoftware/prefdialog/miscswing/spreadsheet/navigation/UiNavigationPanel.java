/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-misc-swing.
 * 
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
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

import static javax.swing.ListSelectionModel.SINGLE_SELECTION;

import java.awt.BorderLayout;

import javax.inject.Inject;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

import com.anrisoftware.prefdialog.miscswing.spreadsheet.table.SheetTable;
import com.anrisoftware.prefdialog.miscswing.spreadsheet.table.SpreadsheetTable;
import com.anrisoftware.prefdialog.miscswing.spreadsheet.table.SpreadsheetTableFactory;

/**
 * Navigation panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class UiNavigationPanel extends JPanel {

	private final JTable table;

	private SpreadsheetTable sheetTable;

	private NavigationModel model;

	@Inject
	UiNavigationPanel(SpreadsheetTableFactory tableFactory,
			NavigationModelImpl model) {
		this(new SheetTable(model));
		this.model = model;
		this.sheetTable = tableFactory.create(table);
	}

	UiNavigationPanel() {
		this(new JTable());
	}

	/**
	 * Create the panel.
	 */
	private UiNavigationPanel(JTable table) {

		this.table = table;
		table.getTableHeader().setVisible(false);
		table.setCellSelectionEnabled(true);
		setLayout(new MigLayout("", "0[grow][256]0", "0[grow]0"));

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(table);

		add(panel, "cell 0 0,grow");
		table.setSelectionMode(SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);

		add(panel, "cell 1 0,grow");
	}

	@Inject
	void setNavigationCellRenderer(NavigationCellRenderer cellRenderer) {
		table.setDefaultRenderer(Object.class, cellRenderer);
		table.setDefaultRenderer(Integer.class, cellRenderer);
	}

	public SpreadsheetTable getSheetTable() {
		return sheetTable;
	}

	public JTable getTable() {
		return table;
	}

	public NavigationModel getModel() {
		return model;
	}
}
