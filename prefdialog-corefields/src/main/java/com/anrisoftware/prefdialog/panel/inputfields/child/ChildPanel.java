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
package com.anrisoftware.prefdialog.panel.inputfields.child;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Font;

import javax.swing.JPanel;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.swingutils.AbstractFieldComponent;
import com.google.inject.Inject;

/**
 * A {@link JPanel} that contains all fields in rows.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ChildPanel extends AbstractFieldComponent<UiChildPanel> implements
		ChildComponent {

	private final UiChildPanel panel;

	private Object value;

	/**
	 * Setups the {@link UiChildPanel}.
	 */
	@Inject
	ChildPanel(UiChildPanel panel) {
		super(panel);
		this.panel = getField();
		setup();
	}

	/**
	 * Sets the font for the child label.
	 */
	private void setup() {
		setBoldFontForChildLabel();
	}

	private void setBoldFontForChildLabel() {
		Font font = panel.getChildLabel().getFont();
		panel.getChildLabel().setFont(
				new Font(font.getFamily(), font.getStyle() | Font.BOLD, font
						.getSize()));
	}

	@Override
	public void setTitle(String title) {
		panel.getChildLabel().setText(title);
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void addField(FieldHandler<?> inputField) {
		int row = addRowToFieldsLayout();
		panel.getScrollPanel().add(inputField.getAWTComponent(),
				format("0, %d", row));
	}

	private int addRowToFieldsLayout() {
		TableLayout layout = (TableLayout) panel.getScrollPanel().getLayout();
		int rows = layout.getNumRow();
		int row = rows - 1;
		layout.insertRow(row, TableLayout.PREFERRED);
		layout.layoutContainer(panel);
		panel.repaint();
		return row;
	}

	@Override
	public boolean isInputValid() {
		return true;
	}
}
