package com.globalscalingsoftware.prefdialog.annotations.fields;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
public @interface Checkbox {

	String value() default "";

	double width() default -1.0;
}
