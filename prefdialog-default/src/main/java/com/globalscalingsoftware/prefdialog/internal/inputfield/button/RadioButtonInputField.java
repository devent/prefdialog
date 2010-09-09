package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import inputfields.RadioButton;

import com.globalscalingsoftware.prefdialog.IValidator;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class RadioButtonInputField extends
		AbstractInputField<RadioButtonsPanel> implements RadioButton {

	@SuppressWarnings("unchecked")
	public RadioButtonInputField(Object value, String fieldName,
			String helpText, IValidator<?> validator) {
		super(fieldName, helpText, new RadioButtonsPanel());
		addEnumFields((Class<? extends Enum<?>>) value.getClass());
		getComponent().setValue(value);
	}

	private void addEnumFields(Class<? extends Enum<?>> enumClass) {
		for (Object constant : enumClass.getEnumConstants()) {
			getComponent().addRadioButton(constant, constant.toString());
		}
	}

	@Override
	public Object getValue() {
		return getComponent().getValue();
	}

}
