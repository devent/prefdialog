package com.globalscalingsoftware.prefdialog.internal.inputfield.group;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.annotations.Group;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class GroupInputField extends AbstractInputField<GroupPanel> {

	public GroupInputField(IReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field,
			IFieldsFactory fieldsFactory) {
		super(reflectionToolbox, parentObject, value, field, Group.class,
				new GroupPanel());

	}

}
