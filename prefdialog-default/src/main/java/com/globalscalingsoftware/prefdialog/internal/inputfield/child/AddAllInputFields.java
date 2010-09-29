package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;
import java.util.Map;

import org.fest.reflect.exception.ReflectionError;

import com.globalscalingsoftware.prefdialog.InputField;
import com.globalscalingsoftware.prefdialog.internal.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultInputField;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

class AddAllInputFields {

	private final ReflectionToolbox reflectionToolbox;
	private final FieldsFactory fieldsFactory;

	public AddAllInputFields(ReflectionToolbox reflectionToolbox,
			FieldsFactory fieldsFactory) {
		this.reflectionToolbox = reflectionToolbox;
		this.fieldsFactory = fieldsFactory;
	}

	public Map<Field, InputField<?>> addAllInto(
			Map<Field, InputField<?>> inputFields, Object parentObject) {
		addAllInputFields(inputFields, parentObject);
		return inputFields;
	}

	private void addAllInputFields(Map<Field, InputField<?>> inputFields,
			Object parentObject) {
		for (Field field : parentObject.getClass().getDeclaredFields()) {
			InputField<?> inputField = createInputField(parentObject,
					reflectionToolbox, field);
			addInputField(inputFields, field, inputField);
		}
	}

	private InputField<?> createInputField(Object parentObject,
			ReflectionToolbox reflectionToolbox, Field field) {
		try {
			Object value = getValueFromField(parentObject, reflectionToolbox,
					field);
			return createInputField(parentObject, field, value);
		} catch (ReflectionError e) {
			return null;
		}
	}

	private Object getValueFromField(Object parentObject,
			ReflectionToolbox reflectionToolbox, Field field) {
		return reflectionToolbox.getValueFrom(field, parentObject);
	}

	private InputField<?> createInputField(Object parentObject, Field field,
			Object value) {
		return fieldsFactory.createField(parentObject, field, value);
	}

	private void addInputField(Map<Field, InputField<?>> inputFields,
			Field field, InputField<?> inputField) {
		if (inputField == null) {
			return;
		}

		setupInputField(inputField);
		putInputField(inputFields, field, inputField);
	}

	private void setupInputField(InputField<?> inputField) {
		if (inputField instanceof AbstractDefaultInputField) {
			setupDefaultInputField(inputField);
		}
		if (inputField instanceof AbstractChildInputField) {
			setupChildInputField(inputField);
		}
		inputField.setup();
	}

	private void setupChildInputField(InputField<?> inputField) {
		AbstractChildInputField<?> ainputfield = (AbstractChildInputField<?>) inputField;
		ainputfield.setFieldsFactory(fieldsFactory);
	}

	private void setupDefaultInputField(InputField<?> inputField) {
		AbstractDefaultInputField<?> ainputfield = (AbstractDefaultInputField<?>) inputField;
		ainputfield.setReflectionToolbox(reflectionToolbox);
	}

	private void putInputField(Map<Field, InputField<?>> inputFields,
			Field field, InputField<?> inputField) {
		inputFields.put(field, inputField);
	}

}
