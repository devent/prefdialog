package com.globalscalingsoftware.prefdialog.internal;

import static java.lang.String.format;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IFormattedTextFieldFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.IValidator;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.google.inject.Inject;

public class FieldsFactory implements IFieldsFactory {

	private final IReflectionToolbox reflectionToolbox;

	private final IFormattedTextFieldFactory fieldsFactory;

	@Inject
	FieldsFactory(IReflectionToolbox reflectionToolbox,
			IFormattedTextFieldFactory fieldsFactory) {
		this.reflectionToolbox = reflectionToolbox;
		this.fieldsFactory = fieldsFactory;
	}

	@Override
	public IInputField createField(Object parentObject, Field field,
			Object value) {
		if (field.getAnnotation(FormattedTextField.class) != null) {
			return createFormattedTextField(parentObject, field, value);
		}

		throw new UnsupportedOperationException(format(
				"The field '%s' have the wrong annotation", field));
	}

	private IInputField createFormattedTextField(Object parentObject,
			Field field, Object value) {
		String fieldName = field.getName();
		String helpText = reflectionToolbox.getHelpText(field);
		IValidator<?> validator = reflectionToolbox.getValidator(field);
		IInputField textfield = fieldsFactory.createFormattedTextField(value,
				fieldName, helpText, validator);

		return textfield;
	}

}
