package com.anrisoftware.prefdialog.miscswing.awtcheck;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marks a method or constructor to be checked that it is run on the AWT thread.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Retention(RUNTIME)
@Target({ METHOD, CONSTRUCTOR })
public @interface OnAwt {

}
