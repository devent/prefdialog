package com.globalscalingsoftware.prefdialog.internal;

import static org.fest.reflect.core.Reflection.constructor;
import static org.fest.reflect.core.Reflection.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.IValidator;
import com.globalscalingsoftware.prefdialog.annotations.HelpText;
import com.globalscalingsoftware.prefdialog.annotations.Validated;

public class ReflectionToolbox implements IReflectionToolbox {

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
		String helptext = annotationHelpText(field);
		if (helptext != null) {
			return helptext;
		}

		return "";
	}

	private String annotationHelpText(Field field) {
		Annotation a = field.getAnnotation(HelpText.class);
		if (a == null) {
			return "";
		} else {
			return getHelpTextFromAnnotation(a);
		}
	}

	private String getHelpTextFromAnnotation(Annotation a) {
		return invokeMethodWithReturnType("value", String.class, a);
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

	static class NoneValidator implements IValidator<Void> {

		@Override
		public boolean isValid(Void value) {
			return true;
		}

	}

	@Override
	public IValidator<?> getValidator(Field field) {
		Annotation a = field.getAnnotation(Validated.class);
		if (a == null) {
			return new NoneValidator();
		} else {
			return createValidatorFromAnnotation(a);
		}
	}

	private IValidator<?> createValidatorFromAnnotation(Annotation a) {
		@SuppressWarnings("unchecked")
		Class<? extends IValidator<?>> validatorClass = invokeMethodWithReturnType(
				"value", Class.class, a);
		return constructor().in(validatorClass).newInstance();
	}
}
