package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

public interface IInputFieldsFactory {

	IInputField create(Class<? extends IInputField> inputFieldClass,
			IReflectionToolbox reflectionToolboox, Object parentObject,
			Object value, Field field,
			IPreferencePanelFactory preferencePanelFactory);

}
