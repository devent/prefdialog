package com.anrisoftware.prefdialog.reflection.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * Read access to the elements of an annotation via reflection.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class AnnotationAccessImpl implements AnnotationAccess {

	private final AnnotationAccessImplLogger log;

	/**
	 * Sets the logger.
	 * 
	 * @param logger
	 *            the {@link AnnotationAccessImplLogger}.
	 */
	@Inject
	AnnotationAccessImpl(AnnotationAccessImplLogger logger) {
		this.log = logger;
	}

	@Override
	public <T> T getElementValue(Class<? extends Annotation> annotationClass,
			Field field) {
		return getElementValue(annotationClass, field, "value");
	}

	@Override
	public <T> T getElementValue(Class<? extends Annotation> annotationClass,
			Field field, String name) {
		Annotation a = field.getAnnotation(annotationClass);
		try {
			return asType(name, a);
		} catch (NoSuchMethodException e) {
			throw log.noSuchMethodError(e, annotationClass, field, name);
		} catch (IllegalAccessException e) {
			throw log.illegalAccessError(e, annotationClass, field, name);
		} catch (InvocationTargetException e) {
			throw log.invocationTargetError(e, annotationClass, field, name);
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T asType(String name, Annotation a)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		return (T) MethodUtils.invokeMethod(a, name);
	}

}
