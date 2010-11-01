package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fest.reflect.exception.ReflectionError;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

class AddAllInputFields {

	private final ReflectionToolbox reflectionToolbox;
	private final FieldsFactory fieldsFactory;

	public AddAllInputFields(ReflectionToolbox reflectionToolbox,
			FieldsFactory fieldsFactory) {
		this.reflectionToolbox = reflectionToolbox;
		this.fieldsFactory = fieldsFactory;
	}

	public Map<Field, FieldHandler<?>> addAllInto(
			Map<Field, FieldHandler<?>> inputFields, Object parentObject) {
		addAllInputFields(inputFields, parentObject);
		return inputFields;
	}

	private void addAllInputFields(Map<Field, FieldHandler<?>> inputFields,
			Object parentObject) {
		List<Field> fields = getPreferenceFields(parentObject);
		for (Field field : fields) {
			FieldHandler<?> inputField = createInputField(parentObject,
					reflectionToolbox, field);
			addInputField(inputFields, field, inputField);
		}
	}

	private List<Field> getPreferenceFields(Object parentObject) {
		Field[] declaredFields = parentObject.getClass().getDeclaredFields();
		List<Field> fields = new ArrayList<Field>(declaredFields.length);
		addPreferenceFields(fields, declaredFields);
		return fields;
	}

	private void addPreferenceFields(List<Field> fields, Field[] declaredFields) {
		for (Field field : declaredFields) {
			if (fieldIsStatic(field)) {
				continue;
			}
			if (fieldIsTransient(field)) {
				continue;
			}
			fields.add(field);
		}
	}

	private boolean fieldIsTransient(Field field) {
		return Modifier.isTransient(field.getModifiers());
	}

	private boolean fieldIsStatic(Field field) {
		return Modifier.isStatic(field.getModifiers());
	}

	private FieldHandler<?> createInputField(Object parentObject,
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

	private FieldHandler<?> createInputField(Object parentObject, Field field,
			Object value) {
		return fieldsFactory.createField(parentObject, field, value);
	}

	private void addInputField(Map<Field, FieldHandler<?>> inputFields,
			Field field, FieldHandler<?> inputField) {
		if (inputField == null) {
			return;
		}

		inputFields.put(field, inputField);
	}

}
