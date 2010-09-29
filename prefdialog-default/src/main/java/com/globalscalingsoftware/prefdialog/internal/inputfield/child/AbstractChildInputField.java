package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

import org.fest.reflect.exception.ReflectionError;

import com.globalscalingsoftware.prefdialog.InputField;
import com.globalscalingsoftware.prefdialog.internal.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.ReflectionToolbox;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultInputField;

public abstract class AbstractChildInputField<ComponentType extends IChildComponent>
		extends AbstractDefaultInputField<ComponentType> {

	private FieldsFactory fieldsFactory;

	private final Map<Field, InputField<?>> inputFields;

	public AbstractChildInputField(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			ComponentType component) {
		super(parentObject, value, field, annotationClass, component);
		inputFields = new HashMap<Field, InputField<?>>();
	}

	@Override
	public void setup() {
		super.setup();
		addAllInputFields();
		setupActions();
	}

	private void addAllInputFields() {
		Object parentObject = getComponentValue();
		ReflectionToolbox reflectionToolbox = getReflectionToolbox();
		addAllInputFields(parentObject, reflectionToolbox);
	}

	private void addAllInputFields(Object parentObject,
			ReflectionToolbox reflectionToolbox) {
		for (Field field : parentObject.getClass().getDeclaredFields()) {
			try {
				Object value = reflectionToolbox.getValueFrom(field,
						parentObject);
				InputField<?> inputField = fieldsFactory.createField(
						parentObject, field, value);
				addInputField(field, inputField);
			} catch (ReflectionError e) {
				continue;
			}
		}
	}

	private void addInputField(Field field, InputField<?> inputField) {
		if (inputField == null) {
			return;
		}

		if (inputField instanceof AbstractDefaultInputField) {
			AbstractDefaultInputField<?> ainputfield = (AbstractDefaultInputField<?>) inputField;
			ainputfield.setReflectionToolbox(getReflectionToolbox());
		}
		if (inputField instanceof AbstractChildInputField) {
			AbstractChildInputField<?> ainputfield = (AbstractChildInputField<?>) inputField;
			ainputfield.setFieldsFactory(fieldsFactory);
		}

		inputField.setup();
		getComponent().addField(inputField);
		inputFields.put(field, inputField);
	}

	private void setupActions() {
		getComponent().setApplyEvent(new Runnable() {

			@Override
			public void run() {
				applyInput();
			}

		});
	}

	public void applyInput() {
		applyInput(getComponentValue());
	}

	@Override
	public void applyInput(Object parent) {
		for (Field field : inputFields.keySet()) {
			InputField<?> inputField = inputFields.get(field);
			inputField.applyInput(parent);
		}
	}

	public void setFieldsFactory(FieldsFactory fieldsFactory) {
		this.fieldsFactory = fieldsFactory;
	}

	public void setApplyAction(Action a) {
		getComponent().setApplyAction(a);
	}

	public void setRestoreAction(Action a) {
		getComponent().setRestoreAction(a);
	}

	public void restoreInput() {
		restoreInput(getComponentValue());
	}

	@Override
	public void restoreInput(Object parent) {
		for (Field field : inputFields.keySet()) {
			InputField<?> inputField = inputFields.get(field);
			inputField.restoreInput(parent);
		}
	}

}