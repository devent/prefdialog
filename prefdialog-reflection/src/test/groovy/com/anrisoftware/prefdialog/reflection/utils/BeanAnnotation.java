package com.anrisoftware.prefdialog.reflection.utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation used to test annotation access.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface BeanAnnotation {

	/**
	 * The value of the annotation.
	 */
	String value() default "";

	/**
	 * The width.
	 */
	double width() default -1.0d;

	/**
	 * Read-only.
	 */
	boolean readonly() default false;

	/**
	 * The title.
	 */
	String title() default "";

}
