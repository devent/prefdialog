package com.globalscalingsoftware.prefdialog.internal;

import inputfields.FormattedTextField;
import inputfields.IInputField;
import inputfields.TextField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IInputFieldsFactory;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.IValidator;
import com.globalscalingsoftware.prefdialog.annotations.InputField;
import com.globalscalingsoftware.prefdialog.internal.formattedtextfield.FormattedTextFieldImpl;
import com.globalscalingsoftware.prefdialog.internal.formattedtextfield.TextFieldImpl;
import com.google.inject.Inject;

public class FieldsFactory implements IFieldsFactory {

	private final IReflectionToolbox reflectionToolbox;

	private final IInputFieldsFactory inputFieldFactory;

	private final Map<Class<? extends IInputField>, Class<? extends IInputField>> inputFieldImplementations;

	@Inject
	FieldsFactory(IReflectionToolbox reflectionToolbox,
			IInputFieldsFactory inputFieldFactory) {
		this.reflectionToolbox = reflectionToolbox;
		this.inputFieldFactory = inputFieldFactory;

		inputFieldImplementations = new HashMap<Class<? extends IInputField>, Class<? extends IInputField>>();
		inputFieldImplementations.put(TextField.class, TextFieldImpl.class);
		inputFieldImplementations.put(FormattedTextField.class,
				FormattedTextFieldImpl.class);
	}

	@Override
	public IInputField createField(Object parentObject, Field field,
			Object value) {

		String fieldName = field.getName();
		String helpText = reflectionToolbox.getHelpText(field);
		IValidator<?> validator = reflectionToolbox.getValidator(field);

		Class<? extends IInputField> inputFieldClass = getInputFieldClassFrom(field);
		return createInputField(value, fieldName, helpText, validator,
				inputFieldClass);
	}

	private IInputField createInputField(Object value, String fieldName,
			String helpText, IValidator<?> validator,
			Class<? extends IInputField> inputFieldClass) {
		IInputField inputField = inputFieldFactory.create(inputFieldClass,
				value, fieldName, helpText, validator);
		return inputField;
	}

	private Class<? extends IInputField> getInputFieldClassFrom(Field field) {
		Annotation a = field.getAnnotation(InputField.class);
		Class<? extends IInputField> inputFieldClass = reflectionToolbox
				.getInputFieldClassFrom(a);
		inputFieldClass = inputFieldImplementations.get(inputFieldClass);
		return inputFieldClass;
	}

}
