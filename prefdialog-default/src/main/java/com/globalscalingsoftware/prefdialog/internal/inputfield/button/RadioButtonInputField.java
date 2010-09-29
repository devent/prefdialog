package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultInputField;

public class RadioButtonInputField extends
		AbstractDefaultInputField<RadioButtonsPanel> {

	private final Object value;

	public RadioButtonInputField(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, RadioButton.class,
				new RadioButtonsPanel());
		this.value = value;
	}

	@Override
	public void setup() {
		super.setup();
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
