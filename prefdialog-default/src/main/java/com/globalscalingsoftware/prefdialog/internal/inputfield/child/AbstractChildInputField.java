package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

import org.fest.reflect.exception.ReflectionError;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public abstract class AbstractChildInputField<ComponentType extends Component & IChildComponent>
		extends AbstractInputField<ComponentType> {

	private IFieldsFactory fieldsFactory;

	private final Map<Field, IInputField> inputFields;

	public AbstractChildInputField(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			ComponentType component) {
		super(parentObject, value, field, annotationClass, component);
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
				addInputField(field, inputField);
			} catch (ReflectionError e) {
				continue;
			}
		}
	}

	private void addInputField(Field field, IInputField inputField) {
		if (inputField == null) {
			return;
		}

		if (inputField instanceof AbstractInputField) {
			AbstractInputField<?> ainputfield = (AbstractInputField<?>) inputField;
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