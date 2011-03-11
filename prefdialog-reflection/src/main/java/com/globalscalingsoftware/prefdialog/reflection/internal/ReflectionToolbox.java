/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.reflection.internal;

import static java.lang.String.format;
import static org.fest.reflect.core.Reflection.constructor;
import static org.fest.reflect.core.Reflection.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.fest.reflect.exception.ReflectionError;

import com.google.inject.Singleton;

@Singleton
public class ReflectionToolbox {

	/**
	 * Return the value from a field.
	 * 
	 * If a getter for this field is defined it tries to use the getter first. A
	 * getter is a method with the pattern <code>FieldType getFieldName()</code>
	 * where <code>FieldType</code> the type of the field and
	 * <code>FieldName</code> the name of the field is.
	 * 
	 * If no such getter is defined, the value of the field will be returned by
	 * using reflection.
	 * 
	 * @param field
	 *            the {@link Field} that provides access to the field of the
	 *            class.
	 * @param object
	 *            the {@link Object} of the field.
	 * @return the {@link Object value} of the field.
	 * @throws ReflectionError
	 *             if there was no getter method and it was an illegal access to
	 *             the field through reflection.
	 */
	public Object getValueFrom(Field field, Object object) {
		try {
			return getValueFromGetter(field, object);
		} catch (ReflectionError e) {
			return getValueFromField(field, object);
		}
	}

	private Object getValueFromGetter(Field field, Object object) {
		String name = getGetterName(field);
		return method(name).in(object).invoke();
	}

	private String getGetterName(Field field) {
		String name = field.getName().substring(0, 1).toUpperCase();
		name = "get" + name + field.getName().substring(1);
		return name;
	}

	private Object getValueFromField(Field field, Object object) {
		try {
			return field.get(object);
		} catch (IllegalAccessException e) {
			throw new ReflectionError(format(
					"Illegal access to field '%s' in object '%s'.", field,
					object), e);
		}
	}

	/**
	 * Sets a value to a field.
	 * 
	 * If a setter for this field is defined it tries to use the setter first. A
	 * setter is a method with the pattern
	 * <code>void setFieldName(FieldType)</code> where <code>FieldType</code>
	 * the type of the field and <code>FieldName</code> the name of the field
	 * is.
	 * 
	 * @param field
	 *            the {@link Field} that provides access to the field of the
	 *            class.
	 * @param object
	 *            the {@link Object} of the field.
	 * @return the new {@link Object value} for the field.
	 * @throws ReflectionError
	 *             if there was no such setter method for the field.
	 */
	public void setValueTo(Field field, Object object, Object value) {
		String name = getSetterName(field);
		invokeMethodWithParameters(name, field.getType(), object, value);
	}

	private String getSetterName(Field field) {
		String name = field.getName().substring(0, 1).toUpperCase();
		name = "set" + name + field.getName().substring(1);
		return name;
	}

	/**
	 * Invoke a method with one parameter and no return value.
	 * 
	 * @param name
	 *            the name of the method.
	 * @param parameterType
	 *            the type of the parameter.
	 * @param object
	 *            the object in which the method is declared.
	 * @param value
	 *            the value for the parameter.
	 * @throws ReflectionError
	 *             if the method can't be found or invoked.
	 */
	public void invokeMethodWithParameters(String name, Class<?> parameterType,
			Object object, Object value) {
		method(name).withParameterTypes(parameterType).in(object).invoke(value);
	}

	/**
	 * Invoke a method with no parameters and a return value.
	 * 
	 * @param <T>
	 *            the return type of the method.
	 * @param methodName
	 *            the name of the method.
	 * @param returnType
	 *            the {@link Class} of the return type.
	 * @param object
	 *            the object in which the method is declared.
	 * @return the return value of the method.
	 * @throws ReflectionError
	 *             if the method can't be found or invoked.
	 */
	public <T> T invokeMethodWithReturnType(String methodName,
			Class<? extends T> returnType, Object object) {
		return method(methodName).withReturnType(returnType).in(object)
				.invoke();
	}

	private boolean annotationHaveMethod(Annotation a, String methodName) {
		try {
			return a.annotationType().getMethod(methodName) != null;
		} catch (SecurityException e) {
			return false;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

	public String getFieldName(Field field) {
		return field.getName();
	}

	public int getColumns(Field field) {
		for (Annotation a : field.getAnnotations()) {
			if (annotationHaveMethod(a, "columns")) {
				return invokeMethodWithReturnType("columns", int.class, a);
			}
		}

		return 1;
	}

	public <T> T searchObjectWithAnnotationValueIn(Object parentObject,
			Class<? extends Annotation> annotationClass, T value,
			Class<? extends T> returnType) {
		for (Field field : parentObject.getClass().getDeclaredFields()) {
			Annotation a = field.getAnnotation(annotationClass);
			if (annotationHaveNotValueMethod(a)) {
				continue;
			}
			T aValue = invokeMethodWithReturnType("value", returnType, a);
			if (aValue.equals(value)) {
				return getValueFrom(parentObject, field);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private <T> T getValueFrom(Object parentObject, Field field) {
		return (T) getValueFrom(field, parentObject);
	}

	private boolean annotationHaveNotValueMethod(Annotation a) {
		return a == null || !annotationHaveMethod(a, "value");
	}

	public <T> T newInstance(Class<? extends T> objectClass,
			Object... parameters) {
		Class<?>[] parameterTypes = new Class[parameters.length];
		for (int i = 0; i < parameterTypes.length; i++) {
			parameterTypes[i] = parameters[i].getClass();
		}
		return constructor().withParameterTypes(parameterTypes).in(objectClass)
				.newInstance(parameters);
	}

	public <T> T newInstance(Class<? extends T> objectClass,
			Class<?>[] parameterTypes, Object... parameters) {
		return constructor().withParameterTypes(parameterTypes).in(objectClass)
				.newInstance(parameters);
	}

}
