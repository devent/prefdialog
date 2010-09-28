package com.globalscalingsoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.globalscalingsoftware.prefdialog.Validator;
import com.globalscalingsoftware.prefdialog.validators.AlwaysValid;

@Target(FIELD)
@Retention(RUNTIME)
public @interface TextField {

	double width() default -1.0;

	Class<? extends Validator<?>> validator() default AlwaysValid.class;

	String validatorText() default "";
}
