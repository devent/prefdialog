package com.anrisoftware.prefdialog.reflection.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.reflection.exceptions.ReflectionError;

/**
 * Logging messages for {@link AnnotationAccessImpl}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class AnnotationAccessImplLogger extends AbstractLogger {

	/**
	 * Creates logger for {@link AnnotationAccessImpl}.
	 */
	AnnotationAccessImplLogger() {
		super(AnnotationAccessImpl.class);
	}

	ReflectionError noSuchMethodError(NoSuchMethodException e,
			Class<? extends Annotation> annotationClass, Field field,
			Class<?> type, String name) {
		ReflectionError ex = new ReflectionError(
				"No such element found in the annotation", e)
				.addContextValue("name", name)
				.addContextValue("annotation", annotationClass)
				.addContextValue("field", field)
				.addContextValue("return type", type);
		log.error(ex.getLocalizedMessage());
		return ex;
	}

	ReflectionError illegalAccessError(IllegalAccessException e,
			Class<? extends Annotation> annotationClass, Field field,
			Class<?> type, String name) {
		ReflectionError ex = new ReflectionError(
				"Illegal access to the element in the annotation", e)
				.addContextValue("name", name)
				.addContextValue("annotation", annotationClass)
				.addContextValue("field", field)
				.addContextValue("return type", type);
		log.error(ex.getLocalizedMessage());
		return ex;
	}

	ReflectionError invocationTargetError(InvocationTargetException e,
			Class<? extends Annotation> annotationClass, Field field,
			Class<?> type, String name) {
		ReflectionError ex = new ReflectionError(
				"Exception thrown in the element of the annotation", e)
				.addContextValue("name", name)
				.addContextValue("annotation", annotationClass)
				.addContextValue("field", field)
				.addContextValue("return type", type);
		log.error(ex.getLocalizedMessage());
		return ex;
	}
}
