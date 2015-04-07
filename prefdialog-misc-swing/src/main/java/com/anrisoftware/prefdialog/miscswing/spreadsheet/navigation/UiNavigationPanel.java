/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import java.awt.Font;

import javax.inject.Inject;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

/**
 * Navigation panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class UiNavigationPanel extends JPanel {

	private final JSpinner currentRow;
	private final JFormattedTextField maximumRow;
	private final JFormattedTextField maximumColumn;
	private final JSpinner currentColumn;
	private final JLabel seperatorLabel;

	/**
	 * Create the panel.
	 */
	UiNavigationPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createHorizontalGlue());

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("",
				"0[64,grow]2[64,grow][pref!][64,grow]2[64,grow]0", "0[]0"));
		add(panel);

		currentColumn = new JSpinner();
		currentColumn.setFont(currentColumn.getFont().deriveFont(
				currentColumn.getFont().getStyle() & ~Font.BOLD));
		panel.add(currentColumn, "cell 0 0,growx");

		maximumColumn = new JFormattedTextField();
		maximumColumn.setHorizontalAlignment(SwingConstants.TRAILING);
		maximumColumn.setEditable(false);
        panel.add(maximumColumn, "cell 1 0,grow");

		seperatorLabel = new JLabel("-");
		panel.add(seperatorLabel, "cell 2 0,alignx center");

		currentRow = new JSpinner();
		currentRow.setFont(currentRow.getFont().deriveFont(
				currentRow.getFont().getStyle() & ~Font.BOLD));
		panel.add(currentRow, "cell 3 0,growx");

		maximumRow = new JFormattedTextField();
		maximumRow.setHorizontalAlignment(SwingConstants.TRAILING);
		maximumRow.setEditable(false);
        panel.add(maximumRow, "cell 4 0,grow");
	}

	@Inject
	void setCurrentRowModel(CurrentRowModel model) {
		currentRow.setModel(model);
	}

	public CurrentRowModel getCurrentRowModel() {
		return (CurrentRowModel) currentRow.getModel();
	}

	@Inject
	void setCurrentColumnModel(CurrentColumnModel model) {
		currentColumn.setModel(model);
	}

	public CurrentColumnModel getCurrentColumnModel() {
		return (CurrentColumnModel) currentColumn.getModel();
	}

	public JSpinner getCurrentRow() {
		return currentRow;
	}

	public JFormattedTextField getMaximumRow() {
		return maximumRow;
	}

	public JFormattedTextField getMaximumColumn() {
		return maximumColumn;
	}

	public JSpinner getCurrentColumn() {
		return currentColumn;
	}

	public JLabel getSeperatorLabel() {
		return seperatorLabel;
	}
}
