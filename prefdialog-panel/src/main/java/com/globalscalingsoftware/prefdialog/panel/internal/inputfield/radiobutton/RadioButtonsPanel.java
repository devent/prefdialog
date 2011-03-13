/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.radiobutton;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractLabelFieldPanel;

public class RadioButtonsPanel extends AbstractLabelFieldPanel<JPanel> {

	private final TableLayout layout;

	private final ButtonGroup buttonsGroup;

	private final Map<ButtonModel, Object> buttons;

	private String name;

	private boolean enabled;

	private int buttonsCount;

	private int rows;

	private int row;

	public RadioButtonsPanel() {
		super(new JPanel());
		this.layout = new TableLayout(new double[] { TableLayout.PREFERRED },
				new double[] {});
		this.buttonsGroup = new ButtonGroup();
		this.buttons = new HashMap<ButtonModel, Object>();
		this.enabled = true;
		this.rows = 0;
		this.row = 0;
		this.buttonsCount = 0;
		setupPanel();
	}

	private void setupPanel() {
		getField().setLayout(layout);
	}

	@Override
	public Object getValue() {
		ButtonModel selected = buttonsGroup.getSelection();
		Object value = buttons.get(selected);
		return value;
	}

	public void setColumnsRows(int columns, int rows) {
		double[] column = new double[columns];
		for (int i = 0; i < columns; i++) {
			column[i] = TableLayout.PREFERRED;
		}
		layout.setColumn(column);

		rows = rows / columns;
		this.rows = rows;
		double[] row = new double[rows];
		for (int i = 0; i < rows; i++) {
			row[i] = TableLayout.PREFERRED;
		}
		layout.setRow(row);
	}

	public void addRadioButton(Object value, String text) {
		JRadioButton button = new JRadioButton(text);
		button.setName(format("%s-%s", name, text));
		button.setEnabled(enabled);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				inputChanged();
			}
		});
		buttons.put(button.getModel(), value);
		buttonsGroup.add(button);

		int column = buttonsCount / rows;
		if (row >= rows) {
			row = 0;
		}
		getField().add(button, format("%d, %d", column, row));

		row++;
		buttonsCount++;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		super.setName(name);
	}

	@Override
	public void setValue(Object value) {
		for (Map.Entry<ButtonModel, Object> entry : buttons.entrySet()) {
			if (entry.getValue() == value) {
				entry.getKey().setSelected(true);
				break;
			}
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		for (ButtonModel button : buttons.keySet()) {
			button.setEnabled(enabled);
		}
	}

	@Override
	public boolean isInputValid() {
		return true;
	}
}
