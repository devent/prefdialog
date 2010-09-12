package com.globalscalingsoftware.prefdialog;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface IReflectionToolbox {

	Object getValueFrom(Field field, Object object);

	void setValueTo(Field field, Object object, Object value);

	String getFieldName(Field field);

	int getColumns(Field field);

	<T> T invokeMethodWithReturnType(String methodName,
			Class<? extends T> returnType, Object object);

	void invokeMethodWithParameters(String name, Class<?> parameterType,
			Object object, Object value);

	<T> Object searchObjectWithAnnotationValueIn(Object parentObject,
			Class<? extends Annotation> annotationClass, T value,
			Class<? extends T> returnType);

	<T> T newInstance(Class<? extends T> objectClass, Object... parameters);

}