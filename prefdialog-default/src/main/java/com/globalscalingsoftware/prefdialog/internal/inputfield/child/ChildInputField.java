package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;

import org.fest.reflect.exception.ReflectionError;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class ChildInputField extends AbstractInputField<ChildPanel> {

	public ChildInputField(IReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field,
			IFieldsFactory fieldsFactory) {
		super(reflectionToolbox, fieldsFactory, parentObject, value, field,
				Child.class, new ChildPanel());
		addAllInputFields();
	}

	private void addAllInputFields() {
		Object parentObject = getValue();
		IFieldsFactory factory = getFieldsFactory();
		IReflectionToolbox reflectionToolbox = getReflectionToolbox();
		addAllInputFields(parentObject, factory, reflectionToolbox);
	}

	private void addAllInputFields(Object parentObject, IFieldsFactory factory,
			IReflectionToolbox reflectionToolbox) {
		for (Field field : parentObject.getClass().getDeclaredFields()) {
			try {
				Object value = reflectionToolbox.getValueFrom(field,
						parentObject);
				IInputField inputField = factory.createField(parentObject,
						field, value);
				addInputField(inputField);
			} catch (ReflectionError e) {
				continue;
			}
		}
	}

	private void addInputField(IInputField inputField) {
		if (inputField == null) {
			return;
		}
		getComponent().addField(inputField);
	}

}
