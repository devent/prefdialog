package com.globalscalingsoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;

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

	int extent() default 10;

	int majorTicks() default 0;

	int minorTicks() default 0;

	boolean paintTicks() default false;

	boolean paintLabels() default false;

	boolean paintTrack() default true;

	boolean snapToTicks() default false;

	Class<? extends BoundedRangeModel> model() default DefaultBoundedRangeModel.class;
}
