package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

public interface IInputFieldsFactory {

	IInputField create(IReflectionToolbox reflectionToolboox,
			IFieldsFactory fieldsFactory,
			Class<? extends IInputField> inputFieldClass, Object parentObject,
			Object value, Field field);

}
