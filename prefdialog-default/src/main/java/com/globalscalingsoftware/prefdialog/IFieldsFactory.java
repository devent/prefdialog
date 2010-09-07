package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

public interface IFieldsFactory {

	IInputField createField(Object parentObject, Field field, Object value);

}
