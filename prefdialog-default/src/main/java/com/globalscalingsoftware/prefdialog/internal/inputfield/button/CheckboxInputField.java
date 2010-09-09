package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import inputfields.IInputField;

import com.globalscalingsoftware.prefdialog.IValidator;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class CheckboxInputField extends AbstractInputField<CheckBoxPanel>
		implements IInputField {

	public CheckboxInputField(Object value, String fieldName, String helpText,
			IValidator<?> validator) {
		super(fieldName, helpText,
				new CheckBoxPanel(fieldName, (Boolean) value));
	}

	@Override
	public Object getValue() {
		return getComponent().isSelected();
	}

}
