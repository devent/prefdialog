package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Font;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.IInputField;

@SuppressWarnings("serial")
public class GroupPanel extends UiGroupPanel implements IChildComponent {

	private Object value;

	public GroupPanel() {
		setupPanel();
	}

	private void setupPanel() {
		setBoldFontForGroupLabel();
	}

	private void setBoldFontForGroupLabel() {
		Font font = getGroupLabel().getFont();
		getGroupLabel().setFont(
				new Font(font.getFamily(), font.getStyle() | Font.BOLD, font
						.getSize()));
	}

	@Override
	public void setFieldWidth(double width) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFieldName(String name) {
		getGroupLabel().setText(name);
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
	public void addField(IInputField inputField) {
		int row = addRowToFieldsLayout();
		getFieldsPanel().add(inputField.getComponent(), format("0, %d", row));
	}

	private int addRowToFieldsLayout() {
		TableLayout layout = (TableLayout) getFieldsPanel().getLayout();
		int rows = layout.getNumRow();
		int row = rows - 1;
		layout.insertRow(row, TableLayout.PREFERRED);
		layout.layoutContainer(this);
		repaint();
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

}
