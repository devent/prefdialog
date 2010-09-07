package com.globalscalingsoftware.prefdialog;

import inputfields.IInputField;

import java.lang.reflect.Field;

public interface IFieldsFactory {

	IInputField createField(Object parentObject, Field field, Object value);

}
