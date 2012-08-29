/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.buttongroup;

import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupPluginModule.ROW_PANEL_NAME;
import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.annotations.HorizontalAlignment;

/**
 * Panel that aligns the buttons to the left, right or middle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
@SuppressWarnings("serial")
class RowPanel extends JPanel {

	private final TableLayout layout;

	RowPanel() {
		this.layout = createLayout();
		setup();
	}

	private TableLayout createLayout() {
		double[] col = { PREFERRED };
		double[] row = { PREFERRED };
		TableLayout layout = new TableLayout(col, row);
		layout.setHGap(3);
		return layout;
	}

	private void setup() {
		setLayout(layout);
	}

	@Override
	public void setName(String name) {
		super.setName(format("%s-%s", name, ROW_PANEL_NAME));
	}

	public void setButtonsRowPanel(ButtonsRowPanel buttonsRowPanel) {
		add(buttonsRowPanel, "0, 0");
	}

	public void setHorizontalAlignment(HorizontalAlignment alignment) {
		switch (alignment) {
		case LEFT:
			setColumns(new double[] { PREFERRED, FILL });
			updateLayout();
			break;
		case RIGHT:
			setColumns(new double[] { FILL, PREFERRED });
			updateLayout();
			break;
		case MIDDLE:
			setColumns(new double[] { FILL, PREFERRED, FILL });
			break;
		}
	}

	private void setColumns(double[] columns) {
		double[] layoutColumns = layout.getColumn();
		for (int i = 0; i < columns.length; i++) {
			if (layoutColumns[i] == columns[i]) {
				continue;
			}
			if (i > layoutColumns.length - 1) {
				layout.insertColumn(i, columns[i]);
			} else {
				layout.setColumn(i, columns[i]);
			}
		}
	}

	private void updateLayout() {
		layout.layoutContainer(this);
		repaint();
	}

}
