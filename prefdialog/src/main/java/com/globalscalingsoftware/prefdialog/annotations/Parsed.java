package com.globalscalingsoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.globalscalingsoftware.prefdialog.IParser;

@Target(FIELD)
@Retention(RUNTIME)
public @interface Parsed {

	Class<? extends IParser<?>> parserClass();

}