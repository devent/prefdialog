package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.fields.Group;

public class GroupInputField extends AbstractChildInputField<GroupPanel> {

	public GroupInputField(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, Group.class, new GroupPanel());

	}

}
