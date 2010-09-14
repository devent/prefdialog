package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

import org.fest.reflect.exception.ReflectionError;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class ChildInputField extends AbstractInputField<ChildPanel> {

	private IFieldsFactory fieldsFactory;

	private final Map<Field, IInputField> inputFields;

	public ChildInputField(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, Child.class, new ChildPanel());
		inputFields = new HashMap<Field, IInputField>();
	}

	@Override
	public void setup() {
		super.setup();
		addAllInputFields();
		setupActions();
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
				addInputField(field, (AbstractInputField<?>) inputField);
			} catch (ReflectionError e) {
				continue;
			}
		}
	}

	private void addInputField(Field field, AbstractInputField<?> inputField) {
		if (inputField == null) {
			return;
		}
		inputField.setReflectionToolbox(getReflectionToolbox());
		inputField.setup();
		getComponent().addField(inputField);
		inputFields.put(field, inputField);
	}

	private void setupActions() {
		getComponent().setApplyEvent(new Runnable() {

			@Override
			public void run() {
				applyAllInput();
			}
		});
	}

	private void applyAllInput() {
		for (Field field : inputFields.keySet()) {
			IInputField inputField = inputFields.get(field);
			Object value = inputField.getValue();
			getReflectionToolbox().setValueTo(field, getValue(), value);
		}
	}

	public void setFieldsFactory(IFieldsFactory fieldsFactory) {
		this.fieldsFactory = fieldsFactory;
	}

	public void setApplyAction(Action a) {
		getComponent().setApplyAction(a);
	}

	public void setRestoreAction(Action a) {
		getComponent().setRestoreAction(a);
	}

}
