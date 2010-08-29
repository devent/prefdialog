package com.globalscalingsoftware.prefdialog.internal;

import static org.fest.reflect.core.Reflection.method;

import java.lang.reflect.Field;

public class ReflectionToolbox {

	public Object getValueFrom(Field field, Object object) {
		String name = getGetterName(field);
		return method(name).in(object).invoke();
	}

	private String getGetterName(Field field) {
		String name = field.getName().substring(0, 1).toUpperCase();
		name = "get" + name + field.getName().substring(1);
		return name;
	}

	private String getSetterName(Field field) {
		String name = field.getName().substring(0, 1).toUpperCase();
		name = "set" + name + field.getName().substring(1);
		return name;
	}

	public void setValueTo(Field field, Object object, Object value) {
		String name = getSetterName(field);
		method(name).withParameterTypes(field.getType()).in(object)
				.invoke(value);
	}

}
