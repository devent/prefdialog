package com.globalscalingsoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.globalscalingsoftware.prefdialog.Validator;

@Target(FIELD)
@Retention(RUNTIME)
public @interface TextField {

	double width() default -1.0;

	Class<? extends Validator<?>> validator() default Validator.AlwaysValidValidator.class;

	String validatorText() default Validator.EMPTY_STRING;
}
