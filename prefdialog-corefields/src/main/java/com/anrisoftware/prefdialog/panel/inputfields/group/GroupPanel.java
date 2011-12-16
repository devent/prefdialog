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
package com.anrisoftware.prefdialog.panel.inputfields.group;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.panel.inputfields.child.ChildComponent;
import com.anrisoftware.prefdialog.swingutils.AbstractFieldComponent;

/**
 * Sets a {@link UiGroupPanel} that will contain the fields that are in the
 * group.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class GroupPanel extends AbstractFieldComponent<UiGroupPanel> implements
		ChildComponent {

	private final UiGroupPanel panel;

	private Object value;

	/**
	 * Set the {@link UiGroupPanel}.
	 */
	GroupPanel() {
		super(new UiGroupPanel());
		panel = getField();
		setupPanel();
	}

	private void setupPanel() {
		setBoldFontForGroupLabel();
	}

	private void setBoldFontForGroupLabel() {
		TitledBorder border = (TitledBorder) panel.getBorder();
		Font font = defaultFontFor(border);
		border.setTitleFont(new Font(font.getFamily(), font.getStyle()
				| Font.BOLD, font.getSize()));
	}

	private Font defaultFontFor(TitledBorder border) {
		Font font = border.getTitleFont();
		font = font == null ? UIManager.getFont("Label.font") : font;
		font = font == null ? new JLabel().getFont() : font;
		return font;
	}

	@Override
	public void setTitle(String title) {
		TitledBorder border = (TitledBorder) panel.getBorder();
		border.setTitle(title);
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
		panel.getFieldsPanel().add(inputField.getAWTComponent(),
				format("0, %d", row));
	}

	private int addRowToFieldsLayout() {
		TableLayout layout = (TableLayout) panel.getFieldsPanel().getLayout();
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
