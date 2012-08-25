package com.anrisoftware.prefdialog.reflection.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Read access to the elements of an annotation.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface AnnotationAccess {

	/**
	 * Returns the value of the annotation element "value".
	 * 
	 * @param annotationClass
	 *            the {@link Class} of the {@link Annotation}.
	 * 
	 * @param field
	 *            the {@link Field} where the annotation is set.
	 * 
	 * @return the value of the annotation field.
	 */
	<T> T getElementValue(Class<? extends Annotation> annotationClass,
			Field field);

	/**
	 * Returns the value of an annotation element.
	 * 
	 * @param annotationClass
	 *            the {@link Class} of the {@link Annotation}.
	 * 
	 * @param field
	 *            the {@link Field} where the annotation is set.
	 * 
	 * @param name
	 *            the name of the annotation element.
	 * 
	 * @return the value of the annotation field.
	 */
	<T> T getElementValue(Class<? extends Annotation> annotationClass,
			Field field, String name);
}
