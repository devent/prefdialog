package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

public interface IInputFieldsFactory {

	<T extends IInputField> T create(Class<T> inputFieldClass,
			Object parentObject, Object value, Field field);

}
