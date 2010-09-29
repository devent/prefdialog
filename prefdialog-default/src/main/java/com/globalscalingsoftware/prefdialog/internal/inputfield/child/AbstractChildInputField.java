package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.InputField;
import com.globalscalingsoftware.prefdialog.internal.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultInputField;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

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

	private void setupActions() {
		getComponent().setApplyEvent(new Runnable() {

			@Override
			public void run() {
				applyInput();
			}

		});
	}

	private void addAllInputFields() {
		fillInputFields();
		for (InputField<?> inputField : inputFields.values()) {
			getComponent().addField(inputField);
		}
	}

	private void fillInputFields() {
		Object parentObject = getComponentValue();
		ReflectionToolbox reflectionToolbox = getReflectionToolbox();
		new AddAllInputFields(reflectionToolbox, fieldsFactory).addAllInto(
				inputFields, parentObject);
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