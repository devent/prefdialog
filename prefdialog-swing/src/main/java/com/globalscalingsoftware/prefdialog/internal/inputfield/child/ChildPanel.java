package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.RunnableActionEvent;

public class ChildPanel implements IChildComponent {

	private final UiChildPanel panel;

	private final RunnableActionEvent applyEvent;

	private final RunnableActionEvent restoreEvent;

	private Object value;

	public ChildPanel() {
		this.panel = new UiChildPanel();
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
	public void setApplyEvent(Runnable applyEvent) {
		this.applyEvent.setEvent(applyEvent);
	}

	@Override
	public void setRestoreAction(Action a) {
		panel.getRestoreButton().setAction(a);
	}

	public void setRestoreEvent(Runnable restoreEvent) {
		this.restoreEvent.setEvent(restoreEvent);
	}

	@Override
	public void setWidth(double width) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String name) {
		panel.getChildLabel().setText(name);
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
	public Component getAWTComponent() {
		return panel;
	}
}
