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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child.group;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Font;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractFieldPanel;
import com.globalscalingsoftware.prefdialog.panel.internal.panel.inputfield.child.ChildComponent;

public class GroupPanel extends AbstractFieldPanel<UiGroupPanel> implements
		ChildComponent {

	private final UiGroupPanel panel;

	private Object value;

	public GroupPanel() {
		super(new UiGroupPanel());
		panel = getField();
		setupPanel();
	}

	private void setupPanel() {
		setBoldFontForGroupLabel();
	}

	private void setBoldFontForGroupLabel() {
		Font font = panel.getGroupLabel().getFont();
		panel.getGroupLabel().setFont(
				new Font(font.getFamily(), font.getStyle() | Font.BOLD, font
						.getSize()));
	}

	@Override
	public void setWidth(double width) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTitle(String title) {
		panel.getGroupLabel().setText(title);
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
	public void setApplyEvent(Runnable applyEvent) {
	}

	@Override
	public void setApplyAction(Action a) {
	}

	@Override
	public void setRestoreAction(Action a) {
	}

	@Override
	public void setRestoreEvent(Runnable e) {
	}

	@Override
	public void setButtonsTransparent(boolean transparent) {
	}
}
