package com.globalscalingsoftware.prefdialog.internal;

import static java.lang.String.format;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IFormattedTextFieldFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.ITextFieldFactory;
import com.globalscalingsoftware.prefdialog.IValidator;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.google.inject.Inject;

public class FieldsFactory implements IFieldsFactory {

	private final IReflectionToolbox reflectionToolbox;

	private final IFormattedTextFieldFactory formattedTextFieldFactory;

	private final ITextFieldFactory textFieldFactory;

	@Inject
	FieldsFactory(IReflectionToolbox reflectionToolbox,
			IFormattedTextFieldFactory formattedTextFieldFactory,
			ITextFieldFactory textFieldFactory) {
		this.reflectionToolbox = reflectionToolbox;
		this.formattedTextFieldFactory = formattedTextFieldFactory;
		this.textFieldFactory = textFieldFactory;
	}

	@Override
	public IInputField createField(Object parentObject, Field field,
			Object value) {

		String fieldName = field.getName();
		String helpText = reflectionToolbox.getHelpText(field);
		IValidator<?> validator = reflectionToolbox.getValidator(field);

		if (field.getAnnotation(FormattedTextField.class) != null) {
			return createFormattedTextField(parentObject, field, value,
					fieldName, helpText, validator);
		} else if (field.getAnnotation(TextField.class) != null) {
			return createTextField(parentObject, field, value, fieldName,
					helpText, validator);
		}

		throw new UnsupportedOperationException(format(
				"The field '%s' have the wrong annotation", field));
	}

	private IInputField createTextField(Object parentObject, Field field,
			Object value, String fieldName, String helpText,
			IValidator<?> validator) {
		IInputField textfield = textFieldFactory.create(value, fieldName,
				helpText, validator);
		return textfield;
	}

	private IInputField createFormattedTextField(Object parentObject,
			Field field, Object value, String fieldName, String helpText,
			IValidator<?> validator) {
		IInputField textfield = formattedTextFieldFactory.create(value,
				fieldName, helpText, validator);
		return textfield;
	}

}
