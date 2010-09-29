package com.globalscalingsoftware.prefdialog.internal.reflection;

import static java.lang.String.format;
import static org.fest.reflect.core.Reflection.constructor;
import static org.fest.reflect.core.Reflection.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.fest.reflect.exception.ReflectionError;

import com.globalscalingsoftware.annotations.Stateless;
import com.globalscalingsoftware.prefdialog.Validator;

@Stateless
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
	 *             the field thought reflection.
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

	public void setValueTo(Field field, Object object, Object value) {
		String name = getSetterName(field);
		invokeMethodWithParameters(name, field.getType(), object, value);
	}

	private String getSetterName(Field field) {
		String name = field.getName().substring(0, 1).toUpperCase();
		name = "set" + name + field.getName().substring(1);
		return name;
	}

	public void invokeMethodWithParameters(String name, Class<?> parameterType,
			Object object, Object value) {
		method(name).withParameterTypes(parameterType).in(object).invoke(value);
	}

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

	static class NoneValidator implements Validator<Object> {

		@Override
		public boolean isValid(Object value) {
			return true;
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

	public <T> Object searchObjectWithAnnotationValueIn(Object parentObject,
			Class<? extends Annotation> annotationClass, T value,
			Class<? extends T> returnType) {
		for (Field field : parentObject.getClass().getDeclaredFields()) {
			Annotation a = field.getAnnotation(annotationClass);
			if (annotationHaveNotValueMethod(a)) {
				continue;
			}
			if (getValueFromAnnotation(a, returnType).equals(value)) {
				return getValueFrom(field, parentObject);
			}
		}
		return null;
	}

	private boolean annotationHaveNotValueMethod(Annotation a) {
		return a == null || !annotationHaveMethod(a, "value");
	}

	private <T> Object getValueFromAnnotation(Annotation a,
			Class<? extends T> returnType) {
		return invokeMethodWithReturnType("value", returnType, a);
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
}
