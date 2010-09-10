package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class CheckboxInputField extends AbstractInputField<CheckBoxPanel>
		implements IInputField {

	public CheckboxInputField(IReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field) {
		super(reflectionToolbox, parentObject, value, field,
				new CheckBoxPanel());
		getComponent().setInputName(getFieldName());
		getComponent().setSelected((Boolean) value);
	}

	@Override
	public Object getValue() {
		return getComponent().isSelected();
	}

}
