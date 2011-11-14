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
package com.globalscalingsoftware.prefdialog.panel.inputfields.radiobutton;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.globalscalingsoftware.prefdialog.swingutils.AbstractLabelFieldPanel;

/**
 * Adds in the panel {@link JRadioButton}s.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class RadioButtonsPanel extends AbstractLabelFieldPanel<JPanel> {

	private final TableLayout layout;

	private final ButtonGroup buttonsGroup;

	private final Map<ButtonModel, Object> buttons;

	private boolean enabled;

	private int buttonsCount;

	private int rows;

	private int row;

	/**
	 * Create and add the {@link JRadioButton}s for the value.
	 */
	RadioButtonsPanel() {
		super(new JPanel());
		this.layout = new TableLayout(new double[] { TableLayout.PREFERRED },
				new double[] {});
		this.buttonsGroup = new ButtonGroup();
		this.buttons = new HashMap<ButtonModel, Object>();
		this.enabled = true;
		this.rows = 0;
		this.row = 0;
		this.buttonsCount = 0;
		setup();
	}

	private void setup() {
		getPanelField().setLayout(layout);
	}

	@Override
	public Object getValue() {
		ButtonModel selected = buttonsGroup.getSelection();
		Object value = buttons.get(selected);
		return value;
	}

	/**
	 * Set the columns and rows for the buttons.
	 */
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

	/**
	 * Add a new {@link JRadioButton}.
	 * 
	 * @param value
	 *            the value of the button.
	 * 
	 * @param text
	 *            the text of the button, is used for the name and the title.
	 */
	public void addRadioButton(Object value, String text) {
		JRadioButton button = createButton(value, text);
		addButtonToPanel(button);
		row++;
		buttonsCount++;
	}

	private void addButtonToPanel(JRadioButton button) {
		int column = buttonsCount / rows;
		if (row >= rows) {
			row = 0;
		}
		getPanelField().add(button, format("%d, %d", column, row));
	}

	private JRadioButton createButton(Object value, String text) {
		JRadioButton button = new JRadioButton(text);
		button.setName(text);
		button.setEnabled(enabled);
		buttons.put(button.getModel(), value);
		buttonsGroup.add(button);
		return button;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		Enumeration<AbstractButton> enu = buttonsGroup.getElements();
		while (enu.hasMoreElements()) {
			AbstractButton button = enu.nextElement();
			button.setName(format("%s-%s", name, button.getName()));
		}
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
