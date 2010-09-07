package com.globalscalingsoftware.prefdialog;

import inputfields.IInputField;

public interface IInputFieldsFactory {

	IInputField create(Class<? extends IInputField> inputFieldClass,
			Object value, String fieldName, String helpText,
			IValidator<?> validator);

}
