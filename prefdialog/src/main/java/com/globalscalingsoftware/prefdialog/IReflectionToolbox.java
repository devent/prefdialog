package com.globalscalingsoftware.prefdialog;

import java.lang.reflect.Field;

public interface IReflectionToolbox {

	Object getValueFrom(Field field, Object object);

	void setValueTo(Field field, Object object, Object value);

}