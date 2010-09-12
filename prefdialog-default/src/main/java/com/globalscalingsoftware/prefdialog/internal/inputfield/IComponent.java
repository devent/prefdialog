package com.globalscalingsoftware.prefdialog.internal.inputfield;

public interface IComponent {

	void setFieldWidth(double width);

	void setFieldName(String name);

	void setValue(Object value);

	Object getValue();
}
