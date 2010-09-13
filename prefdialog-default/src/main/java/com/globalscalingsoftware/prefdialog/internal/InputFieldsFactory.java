package com.globalscalingsoftware.prefdialog.internal;

import static org.fest.reflect.core.Reflection.constructor;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IInputFieldsFactory;

public class InputFieldsFactory implements IInputFieldsFactory {

	@Override
	public <T extends IInputField> T create(Class<T> inputFieldClass,
			Object parentObject, Object value, Field field) {
		Class<?>[] parameterTypes = { Object.class, Object.class, Field.class };
		return constructor().withParameterTypes(parameterTypes)
				.in(inputFieldClass).newInstance(parentObject, value, field);
	}

}
