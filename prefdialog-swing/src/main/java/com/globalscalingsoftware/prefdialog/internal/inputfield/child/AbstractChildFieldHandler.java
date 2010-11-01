package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

public abstract class AbstractChildFieldHandler<ComponentType extends IChildComponent>
		extends AbstractDefaultFieldHandler<ComponentType> {

	private FieldsFactory fieldsFactory;

	private final List<FieldHandler<?>> inputFields;

	private Action applyAction;

	private Action restoreAction;

	public AbstractChildFieldHandler(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			ComponentType component) {
		super(parentObject, value, field, annotationClass, component);
		inputFields = new ArrayList<FieldHandler<?>>();
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
		fillInputFields(inputFields);
		for (FieldHandler<?> inputField : inputFields) {
			setupInputField(inputField);
			getComponent().addField(inputField);
		}
	}

	private void fillInputFields(List<FieldHandler<?>> inputFields) {
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
		for (FieldHandler<?> inputField : inputFields) {
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
		for (FieldHandler<?> inputField : inputFields) {
			inputField.restoreInput(parent);
		}
	}

}