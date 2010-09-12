package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class RadioButtonInputField extends
		AbstractInputField<RadioButtonsPanel> implements IInputField {

	public RadioButtonInputField(IReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field) {
		super(reflectionToolbox, parentObject, value, field, RadioButton.class,
				new RadioButtonsPanel());

		setupComponent(value);
	}

	private void setupComponent(Object value) {
		setupEnumFields(value);
		setupColumns();
	}

	private void setupColumns() {
		int columns = getReflectionToolbox().getColumns(getField());
		getComponent().setColumns(columns);
	}

	private void setupEnumFields(Object value) {
		Class<? extends Enum<?>> valueclass = getValueClass(value);
		addEnumFields(valueclass);
		getComponent().setValue(value);
	}

	private Class<? extends Enum<?>> getValueClass(Object value) {
		@SuppressWarnings("unchecked")
		Class<? extends Enum<?>> valueclass = (Class<? extends Enum<?>>) value
				.getClass();
		return valueclass;
	}

	private void addEnumFields(Class<? extends Enum<?>> enumClass) {
		for (Object constant : enumClass.getEnumConstants()) {
			getComponent().addRadioButton(constant, constant.toString());
		}
	}

}
