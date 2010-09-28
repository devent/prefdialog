package com.globalscalingsoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.globalscalingsoftware.prefdialog.Validator;
import com.globalscalingsoftware.prefdialog.validators.AlwaysValidValidator;

@Target(FIELD)
@Retention(RUNTIME)
public @interface TextField {

	double width() default -1.0;

	Class<? extends Validator<?>> validator() default AlwaysValidValidator.class;

	String validatorText() default "";
}
