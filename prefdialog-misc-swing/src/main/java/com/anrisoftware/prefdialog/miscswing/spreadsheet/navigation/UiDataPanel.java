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

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import net.miginfocom.swing.MigLayout;

import com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingFormattedTextField;

/**
 * Navigation panel.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class UiDataPanel extends JPanel {
	private final ValidatingFormattedTextField currentColumnField;
	private final JFormattedTextField maximumColumnField;
	private final JLabel separatorLabel;
	private final JLabel columnSeparatorLabel;
	private final JLabel rowSeparatorLabel;
	private final ValidatingFormattedTextField currentRowField;
	private final JFormattedTextField maximumRowField;

	/**
	 * Create the panel.
	 */
	public UiDataPanel() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new MigLayout("gap 0 0 fill ins 0",
				"0[grow][10%][][10%][][10%][][10%]0", "0[]0"));

		currentColumnField = new ValidatingFormattedTextField();
		currentColumnField.setHorizontalAlignment(SwingConstants.TRAILING);
		currentColumnField.setName("currentColumnField");
		currentColumnField.setText("0");
		add(currentColumnField, "cell 1 0,growx");
		currentColumnField.setColumns(10);

		columnSeparatorLabel = new JLabel("-");
		columnSeparatorLabel.setName("columnSeparatorLabel");
		add(columnSeparatorLabel, "cell 2 0,alignx trailing");

		maximumColumnField = new JFormattedTextField();
		maximumColumnField.setName("maximumColumnField");
		maximumColumnField.setEditable(false);
		maximumColumnField.setFocusable(false);
		maximumColumnField.setText("6");
		add(maximumColumnField, "cell 3 0,growx");
		maximumColumnField.setColumns(10);

		separatorLabel = new JLabel(":");
		separatorLabel.setName("separatorLabel");
		add(separatorLabel, "cell 4 0,alignx trailing");

		currentRowField = new ValidatingFormattedTextField();
		currentRowField.setHorizontalAlignment(SwingConstants.TRAILING);
		currentRowField.setName("currentRowField");
		currentRowField.setText("0");
		add(currentRowField, "cell 5 0,growx");

		rowSeparatorLabel = new JLabel("-");
		rowSeparatorLabel.setName("rowSeparatorLabel");
		add(rowSeparatorLabel, "cell 6 0");

		maximumRowField = new JFormattedTextField();
		maximumRowField.setEditable(false);
		maximumRowField.setName("maximumRowField");
		maximumRowField.setText("40");
		maximumRowField.setFocusable(false);
		add(maximumRowField, "cell 7 0,growx");

	}

	public ValidatingFormattedTextField getCurrentColumnField() {
		return currentColumnField;
	}

	public JFormattedTextField getMaximumColumnField() {
		return maximumColumnField;
	}

	public ValidatingFormattedTextField getCurrentRowField() {
		return currentRowField;
	}

	public JFormattedTextField getMaximumRowField() {
		return maximumRowField;
	}
}
