package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.InputField;

public class GroupPanel implements IChildComponent {

	private final UiGroupPanel panel;

	private Object value;

	public GroupPanel() {
		panel = new UiGroupPanel();
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
	public void setName(String name) {
		panel.getGroupLabel().setText(name);
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
	public void addField(InputField<?> inputField) {
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
	public Component getAWTComponent() {
		return panel;
	}
}