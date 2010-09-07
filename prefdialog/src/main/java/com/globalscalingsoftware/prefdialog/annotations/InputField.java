package com.globalscalingsoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import inputfields.IInputField;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Target(FIELD)
@Retention(RUNTIME)
public @interface InputField {

	Class<? extends IInputField> value();
}
