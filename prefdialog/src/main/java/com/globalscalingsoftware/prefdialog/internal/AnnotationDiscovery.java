package com.globalscalingsoftware.prefdialog.internal;

import static java.lang.String.format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationDiscovery {

	public void discover(Object object, Filter filter,
			DiscoveredListener listener) throws AnnotationDiscoveryException {
		if (object == null) {
			return;
		}
		discoverFields(object, filter, listener);
	}

	private void discoverFields(Object object, Filter filter,
			DiscoveredListener listener) throws AnnotationDiscoveryException {
		Class<? extends Object> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (filter.accept(annotation)) {

					String name = field.getName().substring(0, 1).toUpperCase();
					name = "get" + name + field.getName().substring(1);
					Method getter = getMethodFrom(clazz, name);

					Object value = invokeMethod(object, getter);
					listener.fieldAnnotationDiscovered(field, value, annotation);
					discover(value, filter, listener);
				}
			}
		}
	}

	private Object invokeMethod(Object object, Method method)
			throws AnnotationDiscoveryException {
		try {
			return method.invoke(object);
		} catch (IllegalArgumentException e) {
			throw new AnnotationDiscoveryException(format(
					"Exception while invoke the " + "method %s(%s).", method,
					object), e);
		} catch (IllegalAccessException e) {
			throw new AnnotationDiscoveryException(format(
					"Exception while invoke the " + "method %s(%s).", method,
					object), e);
		} catch (InvocationTargetException e) {
			throw new AnnotationDiscoveryException(format(
					"Exception while invoke the " + "method %s(%s).", method,
					object), e);
		}
	}

	private Method getMethodFrom(Class<? extends Object> clazz, String name)
			throws AnnotationDiscoveryException {
		try {
			return clazz.getMethod(name);
		} catch (SecurityException e) {
			throw new AnnotationDiscoveryException(format(
					"Exception while get the method %s.%s().", clazz, name), e);
		} catch (NoSuchMethodException e) {
			throw new AnnotationDiscoveryException(format(
					"Exception while get the method %s.%s().", clazz, name), e);
		}
	}

}
