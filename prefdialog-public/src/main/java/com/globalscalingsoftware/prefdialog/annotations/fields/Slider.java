package com.globalscalingsoftware.prefdialog.annotations.fields;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
public @interface Slider {

	String value() default "";

	int columns() default 1;

	double width() default -1.0;

	/**
	 * If this input field should be read-only. Read-only fields are to show
	 * information for the user without that the user can modify the value.
	 */
	boolean readonly() default false;

	int min() default 0;

	int max() default 100;
}
