package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import static java.lang.String.format;
import info.clearthought.layout.TableLayout;

import java.awt.Font;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.IComponent;

@SuppressWarnings("serial")
public class ChildPanel extends UiChildPanel implements IComponent {

	private Object value;

	public ChildPanel() {
		setupPanel();
	}

	private void setupPanel() {
		setBoldFontForChildLabel();
	}

	private void setBoldFontForChildLabel() {
		Font font = getChildLabel().getFont();
		getChildLabel().setFont(
				new Font(font.getFamily(), font.getStyle() | Font.BOLD, font
						.getSize()));
	}

	@Override
	public void setFieldWidth(double width) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFieldName(String name) {
		getChildLabel().setText(name);
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

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
}
