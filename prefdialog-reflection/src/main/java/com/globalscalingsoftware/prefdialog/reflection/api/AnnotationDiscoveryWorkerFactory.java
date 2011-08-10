package com.globalscalingsoftware.prefdialog.reflection.api;

import java.lang.annotation.Annotation;

import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new {@link AnnotationDiscoveryWorker}.
 */
public interface AnnotationDiscoveryWorkerFactory {

	/**
	 * Creates a new {@link AnnotationDiscoveryWorker}.
	 * 
	 * @param filter
	 *            the {@link AnnotationFilter} that will be used to filter
	 *            {@link Annotation}s.
	 * 
	 * @param callback
	 *            the {@link AnnotationDiscoveryCallback} that will be called if
	 *            a annotation is found.
	 * 
	 * @return the new created {@link AnnotationDiscoveryWorker}.
	 */
	AnnotationDiscoveryWorker create(@Assisted AnnotationFilter filter,
			@Assisted AnnotationDiscoveryCallback callback);
}
