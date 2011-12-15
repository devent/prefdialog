package com.anrisoftware.prefdialog.reflection.api;

import java.lang.annotation.Annotation;


/**
 * Search an object's fields for {@link Annotation}s and if an annotation is
 * found it will call a {@link AnnotationDiscoveryCallback callback}. The
 * annotations are defined by a {@link AnnotationFilter filter}
 * 
 * @see AnnotationFilter
 * @see AnnotationDiscoveryCallback
 * @see Annotation
 */
public interface AnnotationDiscoveryWorker {

	/**
	 * Discovers the {@link Annotation annotations} in the given object's
	 * fields.
	 */
	void discoverAnnotations(Object object);
}
