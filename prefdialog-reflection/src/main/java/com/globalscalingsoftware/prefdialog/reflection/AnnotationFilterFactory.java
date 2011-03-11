package com.globalscalingsoftware.prefdialog.reflection;

import java.lang.annotation.Annotation;
import java.util.Collection;

import com.globalscalingsoftware.prefdialog.reflection.internal.AnnotationFilter;
import com.google.inject.assistedinject.Assisted;

/**
 * A factory for {@link AnnotationFilter annotation filter}.
 */
public interface AnnotationFilterFactory {

	/**
	 * Creates a new {@link AnnotationFilter}.
	 * 
	 * @param annotations
	 *            a collection of {@link Annotation annotations} that the filter
	 *            will accept.
	 * @return the new created {@link AnnotationFilter annotation filter}.
	 */
	AnnotationFilter create(
			@Assisted Collection<Class<? extends Annotation>> annotations);
}
