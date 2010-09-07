package com.globalscalingsoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.globalscalingsoftware.prefdialog.IValidator;

@Target(FIELD)
@Retention(RUNTIME)
public @interface Validated {

	Class<? extends IValidator<?>> value();

}
