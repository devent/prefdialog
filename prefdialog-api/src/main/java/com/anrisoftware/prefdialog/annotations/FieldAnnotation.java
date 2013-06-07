package com.anrisoftware.prefdialog.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marks annotations as preference dialog fields.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Target(ANNOTATION_TYPE)
@Retention(RUNTIME)
public @interface FieldAnnotation {

}
