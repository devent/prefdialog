package com.globalscalingsoftware.prefdialog.internal.inputfield;

import java.awt.Component;

import javax.swing.JComponent;

import com.globalscalingsoftware.prefdialog.FieldComponent;

public abstract class AbstractFieldPanel<FieldType extends JComponent>
		implements FieldComponent {

	private final FieldType field;

	public AbstractFieldPanel(FieldType field) {
		this.field = field;
	}

	public FieldType getField() {
		return field;
	}

	@Override
	public abstract void setValue(Object value);

	@Override
	public abstract Object getValue();

	@Override
	public void setName(String name) {
		field.setName(name);
	}

	@Override
	public void setWidth(double width) {
	}

	@Override
	public Component getAWTComponent() {
		return field;
	}
}
