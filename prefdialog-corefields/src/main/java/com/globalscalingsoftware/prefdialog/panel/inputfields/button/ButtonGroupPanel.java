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
package com.globalscalingsoftware.prefdialog.panel.inputfields.button;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.annotations.HorizontalPositions;
import com.globalscalingsoftware.prefdialog.swingutils.AbstractLabelFieldPanel;

/**
 * Creates a new panel that aligns the buttons in a row.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
class ButtonGroupPanel extends AbstractLabelFieldPanel<JPanel> {

	private TableLayout layout;

	private List<Action> buttonActions;

	private String name;

	private final List<JButton> buttons;

	/**
	 * Create the {@link JPanel} and sets a one-row {@link TableLayout}.
	 */
	ButtonGroupPanel() {
		super(new JPanel());
		this.name = "";
		this.buttons = new ArrayList<JButton>();
		setup();
	}

	private void setup() {
		double[] col = {};
		double[] row = { TableLayout.PREFERRED };
		layout = new TableLayout(col, row);
		layout.setHGap(3);
		getPanelField().setLayout(layout);
	}

	@Override
	public void setValue(Object value) {
		buttonActions = getButtonActions(value);
		for (int i = 0; i < buttonActions.size(); i++) {
			addButton(i, buttonActions.get(i));
		}
		updateLayout();
	}

	private void updateLayout() {
		layout.layoutContainer(getPanelField());
		getPanelField().repaint();
	}

	private void addButton(int i, Action action) {
		layout.insertColumn(i, TableLayout.PREFERRED);
		JButton button = new JButton(action);
		button.setName(format("button-%d-%s", i, name));
		buttons.add(button);
		getPanelField().add(button, format("%d, 0", i));
	}

	@SuppressWarnings("unchecked")
	private List<Action> getButtonActions(Object value) {
		return (List<Action>) value;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		this.name = name;
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).setName(format("button-%d-%s", i, name));
		}
	}

	@Override
	public Object getValue() {
		return buttonActions;
	}

	@Override
	public boolean isInputValid() {
		return true;
	}

	public void setHorizontalPosition(HorizontalPositions position) {
		switch (position) {
		case RIGHT:
			layout.insertColumn(0, TableLayout.FILL);
			updateLayout();
		}
	}

}
