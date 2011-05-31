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
		extends AbstractPanelField<FieldType> {

	private final JLabel label;

	public AbstractLabelFieldPanel(FieldType field) {
		super(field);
		this.label = new JLabel();
		setLayout(createLayout());
		setupPanel();
	}

	private TableLayout createLayout() {
		double[] col = { TableLayout.FILL };
		double[] row = { TableLayout.PREFERRED, TableLayout.PREFERRED };
		return new TableLayout(col, row);
	}

	private void setupPanel() {
		JPanel panel = (JPanel) getAWTComponent();
		panel.setLayout(getLayout());

		panel.add(label, "0, 0");
		panel.add(getPanelField(), "0, 1");

		label.setLabelFor(getPanelField());
		getPanelField().requestFocus();
	}

	@Override
	public void setTitle(String title) {
		label.setText(title + ": ");
	}

	public void setLabelText(String text) {
		label.setText(text);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		label.setName("label-" + name);
	}

	public void setShowTitle(boolean show) {
		if (!show) {
			label.setText("");
		}
	}

}