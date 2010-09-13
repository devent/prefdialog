package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;

import org.fest.reflect.exception.ReflectionError;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class ChildInputField extends AbstractInputField<ChildPanel> {

	private IFieldsFactory fieldsFactory;

	public ChildInputField(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, Child.class, new ChildPanel());
	}

	@Override
	public void setup() {
		super.setup();
		addAllInputFields();
	}

	private void addAllInputFields() {
		Object parentObject = getValue();
		IReflectionToolbox reflectionToolbox = getReflectionToolbox();
		addAllInputFields(parentObject, reflectionToolbox);
	}

	private void addAllInputFields(Object parentObject,
			IReflectionToolbox reflectionToolbox) {
		for (Field field : parentObject.getClass().getDeclaredFields()) {
			try {
				Object value = reflectionToolbox.getValueFrom(field,
						parentObject);
				IInputField inputField = fieldsFactory.createField(
						parentObject, field, value);
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

	public void setFieldsFactory(IFieldsFactory fieldsFactory) {
		this.fieldsFactory = fieldsFactory;
	}
}
