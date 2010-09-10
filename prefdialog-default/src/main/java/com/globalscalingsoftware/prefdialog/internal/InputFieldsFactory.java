package com.globalscalingsoftware.prefdialog.internal;

import static org.fest.reflect.core.Reflection.constructor;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IInputFieldsFactory;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;

public class InputFieldsFactory implements IInputFieldsFactory {

	@Override
	public IInputField create(Class<? extends IInputField> inputFieldClass,
			IReflectionToolbox reflectionToolboox, Object value, Field field) {
		Class<?>[] parameterTypes = { IReflectionToolbox.class, Object.class,
				Field.class };
		return constructor().withParameterTypes(parameterTypes)
				.in(inputFieldClass)
				.newInstance(reflectionToolboox, value, field);
	}

}
