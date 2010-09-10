package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

public interface IInputFieldsFactory {

	IInputField create(Class<? extends IInputField> inputFieldClass,
			IReflectionToolbox reflectionToolboox, Object value, Field field);

}
