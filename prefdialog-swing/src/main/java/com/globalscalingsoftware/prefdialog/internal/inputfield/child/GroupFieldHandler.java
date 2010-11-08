package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.fields.Group;

public class GroupFieldHandler extends AbstractChildFieldHandler<GroupPanel> {

	public GroupFieldHandler(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, Group.class, new GroupPanel());
	}

	@Override
	public void applyInput(Object parent) {
		parent = getComponentValue();
		super.applyInput(parent);
	}
}
