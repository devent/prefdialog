package com.globalscalingsoftware.prefdialog.internal;

import static org.fest.reflect.core.Reflection.constructor;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IInputFieldsFactory;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;

public class InputFieldsFactory implements IInputFieldsFactory {

	@Override
	public IInputField create(IReflectionToolbox reflectionToolboox,
			IFieldsFactory fieldsFactory,
			Class<? extends IInputField> inputFieldClass, Object parentObject,
			Object value, Field field) {
		Class<?>[] parameterTypes = { IReflectionToolbox.class, Object.class,
				Object.class, Field.class, IFieldsFactory.class };
		return constructor()
				.withParameterTypes(parameterTypes)
				.in(inputFieldClass)
				.newInstance(reflectionToolboox, parentObject, value, field,
						fieldsFactory);
	}

}
