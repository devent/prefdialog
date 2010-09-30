package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;

public class ChildFieldHandler extends AbstractChildFieldHandler<ChildPanel> {

	public ChildFieldHandler(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, Child.class, new ChildPanel());
	}

}
