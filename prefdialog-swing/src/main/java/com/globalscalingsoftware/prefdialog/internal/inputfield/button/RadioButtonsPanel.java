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
package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import static java.lang.String.format;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractLabelFieldPanel;

public class RadioButtonsPanel extends AbstractLabelFieldPanel<JPanel> {

	private final GridLayout layout;

	private final ButtonGroup buttonsGroup;

	private final Map<ButtonModel, Object> buttons;

	private String name;

	private boolean enabled;

	public RadioButtonsPanel() {
		super(new JPanel());
		layout = new GridLayout(0, 1);
		buttonsGroup = new ButtonGroup();
		buttons = new HashMap<ButtonModel, Object>();
		enabled = true;
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

	public void addRadioButton(Object value, String text) {
		int rows = layout.getRows();
		layout.setRows(rows + 1);
		JRadioButton button = new JRadioButton(text);
		button.setName(format("%s-%s", name, text));
		button.setEnabled(enabled);
		buttons.put(button.getModel(), value);
		buttonsGroup.add(button);
		getField().add(button);
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

	public void setColumns(int columns) {
		int rows = layout.getRows() / columns;
		layout.setRows(rows);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		for (ButtonModel button : buttons.keySet()) {
			button.setEnabled(enabled);
		}
	}
}
