package com.globalscalingsoftware.prefdialog.internal;

import static org.fest.reflect.core.Reflection.constructor;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IInputFieldsFactory;
import com.globalscalingsoftware.prefdialog.IValidator;

public class InputFieldsFactory implements IInputFieldsFactory {

	@Override
	public IInputField create(Class<? extends IInputField> inputFieldClass,
			Object value, String fieldName, String helpText,
			IValidator<?> validator) {
		Class<?>[] parameterTypes = { Object.class, String.class, String.class,
				IValidator.class };
		return constructor().withParameterTypes(parameterTypes)
				.in(inputFieldClass)
				.newInstance(value, fieldName, helpText, validator);
	}

}
