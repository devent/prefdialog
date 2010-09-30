package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.fields.Checkbox;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;

public class CheckboxInputField extends
		AbstractDefaultFieldHandler<CheckBoxPanel> {

	public CheckboxInputField(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, Checkbox.class, new CheckBoxPanel());
	}

}
