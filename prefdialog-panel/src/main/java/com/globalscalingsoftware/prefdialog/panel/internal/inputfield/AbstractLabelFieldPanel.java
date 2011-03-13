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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield;

import info.clearthought.layout.TableLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AbstractLabelFieldPanel<FieldType extends JComponent>
		extends AbstractFieldComponent<JPanel> {

	private final JLabel label;

	private final TableLayout layout;

	private final FieldType field;

	public AbstractLabelFieldPanel(FieldType field) {
		super(new JPanel());
		this.field = field;
		this.layout = createLayout();
		this.label = new JLabel();
		setupPanel();
	}

	private TableLayout createLayout() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED };
		return new TableLayout(col, row);
	}

	private void setupPanel() {
		JPanel panel = (JPanel) getAWTComponent();
		panel.setLayout(layout);

		panel.add(label, "0, 0");
		panel.add(field, "0, 1");

		label.setLabelFor(field);
		field.requestFocus();
	}

	public FieldType getPanelField() {
		return field;
	}

	@Override
	public abstract Object getValue();

	@Override
	public void setTitle(String title) {
		label.setText(title + ": ");
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	@Override
	public void setName(String name) {
		super.setName("panel-" + name);
		label.setName("label-" + name);
		field.setName(name);
	}

	@Override
	public void setWidth(double width) {
		JPanel panel = (JPanel) getAWTComponent();
		layout.setColumn(0, width);
		layout.layoutContainer(panel);
		panel.repaint();
	}

	@Override
	public void setEnabled(boolean enabled) {
		field.setEnabled(enabled);
	}

}