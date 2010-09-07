package com.globalscalingsoftware.prefdialog.internal;

import static org.fest.reflect.core.Reflection.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IAnnotationFilter;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.google.inject.Inject;

public class ReflectionToolbox implements IReflectionToolbox {

	private final IAnnotationFilter annotationFilter;

	@Inject
	ReflectionToolbox(IAnnotationFilter annotationFilter) {
		this.annotationFilter = annotationFilter;
	}

	@Override
	public Object getValueFrom(Field field, Object object) {
		String name = getGetterName(field);
		return method(name).in(object).invoke();
	}

	private String getGetterName(Field field) {
		String name = field.getName().substring(0, 1).toUpperCase();
		name = "get" + name + field.getName().substring(1);
		return name;
	}

	@Override
	public void setValueTo(Field field, Object object, Object value) {
		String name = getSetterName(field);
		invokeMethodWithParameters(name, field.getType(), object, value);
	}

	private String getSetterName(Field field) {
		String name = field.getName().substring(0, 1).toUpperCase();
		name = "set" + name + field.getName().substring(1);
		return name;
	}

	private void invokeMethodWithParameters(String name,
			Class<?> parameterType, Object object, Object value) {
		method(name).withParameterTypes(parameterType).in(object).invoke(value);
	}

	@Override
	public String getHelpText(Field field) {
		for (Annotation a : field.getAnnotations()) {
			if (annotationFilter.accept(a)) {
				if (annotationHaveMethod(a, "helpText")) {
					return invokeMethodWithReturnType("helpText", String.class,
							a);
				}
			}
		}
		return "";
	}

	private <T> T invokeMethodWithReturnType(String methodName,
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
}
