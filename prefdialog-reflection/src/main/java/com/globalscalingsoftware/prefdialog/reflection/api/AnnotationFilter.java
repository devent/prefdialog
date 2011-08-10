package com.globalscalingsoftware.prefdialog.reflection.api;

import java.lang.annotation.Annotation;

/**
 * A filter that only accepts specific {@link Annotation annotations}.
 */
public interface AnnotationFilter {

	/**
	 * Returns <code>true</code> if the given {@link Annotation} is accepted,
	 * <code>false</code> if not.
	 */
	boolean accept(Annotation annotation);
}
