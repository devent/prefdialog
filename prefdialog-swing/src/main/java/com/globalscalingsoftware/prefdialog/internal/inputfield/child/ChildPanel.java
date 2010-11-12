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
package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Font;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractFieldPanel;

public class ChildPanel extends AbstractFieldPanel<UiChildPanel> implements
		IChildComponent {

	private final UiChildPanel panel;

	private final RunnableActionEvent applyEvent;

	private final RunnableActionEvent restoreEvent;

	private Object value;

	public ChildPanel() {
		super(new UiChildPanel());
		this.panel = getField();
		this.applyEvent = new RunnableActionEvent();
		this.restoreEvent = new RunnableActionEvent();
		setupPanel();
	}

	private void setupPanel() {
		setBoldFontForChildLabel();
		setupActions();
	}

	private void setBoldFontForChildLabel() {
		Font font = panel.getChildLabel().getFont();
		panel.getChildLabel().setFont(
				new Font(font.getFamily(), font.getStyle() | Font.BOLD, font
						.getSize()));
	}

	private void setupActions() {
		panel.getApplyButton().addActionListener(applyEvent);
		panel.getRestoreButton().addActionListener(restoreEvent);
	}

	@Override
	public void setApplyAction(Action a) {
		panel.getApplyButton().setAction(a);
	}

	@Override
	public void setApplyEvent(Runnable e) {
		this.applyEvent.setEvent(e);
	}

	@Override
	public void setRestoreAction(Action a) {
		panel.getRestoreButton().setAction(a);
	}

	@Override
	public void setRestoreEvent(Runnable e) {
		this.restoreEvent.setEvent(e);
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

}
