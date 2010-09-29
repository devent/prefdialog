package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.fields.Child;

public class ChildInputField extends AbstractChildInputField<ChildPanel> {

	public ChildInputField(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, Child.class, new ChildPanel());
	}

}
