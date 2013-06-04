package com.anrisoftware.prefdialog.classtask;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Factory to create the class task.
 * 
 * @see ClassTask
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface ClassTaskFactory {

	/**
	 * Creates the class task.
	 * 
	 * @param attributeName
	 *            the annotation attribute name that returns the class name.
	 * 
	 * @param annotationClass
	 *            the {@link Class} type of the annotation.
	 * 
	 * @param accessibleObject
	 *            the {@link Field} or the {@link Method} where the annotation
	 *            is added.
	 * 
	 * @return the {@link ClassTask}.
	 */
	ClassTask<?> create(String attributeName,
			Class<? extends Annotation> annotationClass,
			AccessibleObject accessibleObject);
}
