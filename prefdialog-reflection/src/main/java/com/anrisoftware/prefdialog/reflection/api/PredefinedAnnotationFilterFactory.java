package com.anrisoftware.prefdialog.reflection.api;

import java.lang.annotation.Annotation;

import com.google.inject.assistedinject.Assisted;

/**
 * A factory for {@link AnnotationFilter} that accepts {@link Annotation} from a
 * given list.
 */
public interface PredefinedAnnotationFilterFactory {

	/**
	 * Creates a new predefined {@link AnnotationFilter}.
	 * 
	 * @param annotations
	 *            a collection of {@link Annotation annotations} that the filter
	 *            will accept.
	 * 
	 * @return the new created {@link AnnotationFilter annotation filter}.
	 */
	AnnotationFilter create(
			@Assisted Iterable<Class<? extends Annotation>> annotations);
}
