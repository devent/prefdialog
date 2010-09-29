package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

public interface InputFieldComponent {

	void setWidth(double width);

	void setName(String name);

	void setValue(Object value);

	Object getValue();

	Component getAWTComponent();
}
