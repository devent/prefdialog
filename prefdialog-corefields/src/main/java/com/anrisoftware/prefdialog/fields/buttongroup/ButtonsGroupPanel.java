/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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

import static com.anrisoftware.prefdialog.fields.buttongroup.ButtonGroupService.BUTTONS_GROUP_PANEL_NAME;
import static info.clearthought.layout.TableLayoutConstants.FILL;
import static info.clearthought.layout.TableLayoutConstants.PREFERRED;
import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.notNull;
import info.clearthought.layout.TableLayout;

import java.awt.Container;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.annotations.HorizontalAlignment;

/**
 * Panel that aligns the buttons to the left, right or middle.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class ButtonsGroupPanel extends JPanel {

	private final TableLayout layout;

	private Container buttonsPanel;

	private HorizontalAlignment horizontalAlignment;

	ButtonsGroupPanel() {
		this.layout = createLayout();
		this.horizontalAlignment = HorizontalAlignment.RIGHT;
		setupPanel();
	}

	private TableLayout createLayout() {
		double[] col = { PREFERRED };
		double[] row = { PREFERRED };
		TableLayout layout = new TableLayout(col, row);
		layout.setHGap(3);
		return layout;
	}

	private void setupPanel() {
		setLayout(layout);
	}

	@Override
	public void setName(String name) {
		super.setName(format("%s-%s", name, BUTTONS_GROUP_PANEL_NAME));
	}

	/**
	 * Sets the container that contains the buttons.
	 * 
	 * @param panel
	 *            the {@link Container}.
	 */
	public void setButtonsRowPanel(Container panel) {
		if (buttonsPanel != null) {
			remove(buttonsPanel);
		}
		buttonsPanel = panel;
		updateHorizontalAlignment();
	}

	/**
	 * Returns the horizontal alignment of the buttons in this group.
	 * 
	 * @return the {@link HorizontalAlignment}.
	 */
	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	/**
	 * Sets the horizontal alignment of the buttons in this group.
	 * 
	 * @param alignment
	 *            the {@link HorizontalAlignment}.
	 */
	public void setHorizontalAlignment(HorizontalAlignment alignment) {
		notNull(alignment, "The horizontal alignment cannot be null.");
		if (alignment == horizontalAlignment) {
			return;
		}
		horizontalAlignment = alignment;
		updateHorizontalAlignment();
	}

	private void updateHorizontalAlignment() {
		switch (horizontalAlignment) {
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
		remove(buttonsPanel);
		double[] layoutColumns = layout.getColumn();
		for (int i = 0; i < columns.length; i++) {
			if (i > layoutColumns.length - 1) {
				layout.insertColumn(i, columns[i]);
			} else {
				layout.setColumn(i, columns[i]);
			}
			if (columns[i] == PREFERRED) {
				add(buttonsPanel, String.format("%d,0", i));
			}
		}
	}

	private void updateLayout() {
		layout.layoutContainer(this);
		repaint();
	}

}
