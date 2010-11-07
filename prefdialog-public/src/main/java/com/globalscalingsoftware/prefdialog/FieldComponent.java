package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

public interface FieldComponent {

	void setWidth(double width);

	void setName(String name);

	void setTitle(String title);

	void setValue(Object value);

	Object getValue();

	Component getAWTComponent();
}
