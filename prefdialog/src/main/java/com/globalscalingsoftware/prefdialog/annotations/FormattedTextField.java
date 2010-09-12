package com.globalscalingsoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.globalscalingsoftware.prefdialog.IValidator;

@Target(FIELD)
@Retention(RUNTIME)
public @interface FormattedTextField {

	double width() default -1.0;

	Class<? extends IValidator<?>> validator() default IValidator.AlwaysValidVaidator.class;

	String validatorText() default IValidator.EMPTY_STRING;
}
