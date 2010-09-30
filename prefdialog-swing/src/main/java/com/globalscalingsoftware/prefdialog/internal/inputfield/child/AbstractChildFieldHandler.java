package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

public abstract class AbstractChildFieldHandler<ComponentType extends IChildComponent>
		extends AbstractDefaultFieldHandler<ComponentType> {

	private FieldsFactory fieldsFactory;

	private final Map<Field, FieldHandler<?>> inputFields;

	private Action applyAction;

	private Action restoreAction;

	public AbstractChildFieldHandler(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			ComponentType component) {
		super(parentObject, value, field, annotationClass, component);
		inputFields = new HashMap<Field, FieldHandler<?>>();
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
		for (FieldHandler<?> inputField : inputFields.values()) {
			setupInputField(inputField);
			getComponent().addField(inputField);
		}
	}

	private void fillInputFields() {
		Object parentObject = getComponentValue();
		ReflectionToolbox reflectionToolbox = getReflectionToolbox();
		new AddAllInputFields(reflectionToolbox, fieldsFactory).addAllInto(
				inputFields, parentObject);
	}

	private void setupInputField(FieldHandler<?> inputField) {
		if (inputField instanceof AbstractDefaultFieldHandler) {
			setupDefaultInputField(inputField);
		}
		if (inputField instanceof AbstractChildFieldHandler) {
			setupChildInputField(inputField);
		}
		inputField.setup();
	}

	private void setupChildInputField(FieldHandler<?> inputField) {
		AbstractChildFieldHandler<?> ainputfield = (AbstractChildFieldHandler<?>) inputField;
		ainputfield.setFieldsFactory(fieldsFactory);
		ainputfield.setApplyAction(applyAction);
		ainputfield.setRestoreAction(restoreAction);
	}

	private void setupDefaultInputField(FieldHandler<?> inputField) {
		AbstractDefaultFieldHandler<?> ainputfield = (AbstractDefaultFieldHandler<?>) inputField;
		ReflectionToolbox reflectionToolbox = getReflectionToolbox();
		ainputfield.setReflectionToolbox(reflectionToolbox);
	}

	public void applyInput() {
		applyInput(getComponentValue());
	}

	@Override
	public void applyInput(Object parent) {
		for (Field field : inputFields.keySet()) {
			FieldHandler<?> inputField = inputFields.get(field);
			inputField.applyInput(parent);
		}
	}

	public void setFieldsFactory(FieldsFactory fieldsFactory) {
		this.fieldsFactory = fieldsFactory;
	}

	public void setApplyAction(Action a) {
		this.applyAction = a;
		getComponent().setApplyAction(a);
	}

	public void setRestoreAction(Action a) {
		this.restoreAction = a;
		getComponent().setRestoreAction(a);
	}

	public void restoreInput() {
		restoreInput(getComponentValue());
	}

	@Override
	public void restoreInput(Object parent) {
		for (Field field : inputFields.keySet()) {
			FieldHandler<?> inputField = inputFields.get(field);
			inputField.restoreInput(parent);
		}
	}

}