package com.globalscalingsoftware.prefdialog.reflection;

import java.lang.annotation.Annotation;

import com.globalscalingsoftware.prefdialog.reflection.internal.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.reflection.internal.AnnotationFilter;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory for {@link AnnotationDiscovery}.
 */
public interface AnnotationDiscoveryFactory {

	/**
	 * Creates a new {@link AnnotationDiscovery}.
	 * 
	 * @param filter
	 *            the {@link AnnotationFilter filter} that will be used to
	 *            filter {@link Annotation annotations}.
	 * @param object
	 *            the {@link Object object} for which the field's are searched.
	 * @param callback
	 *            the {@link AnnotationDiscoveryCallback callback} that will be
	 *            called if a annotation is found.
	 * @return the new created {@link AnnotationDiscovery}.
	 */
	AnnotationDiscovery create(@Assisted AnnotationFilter filter,
			@Assisted Object object,
			@Assisted AnnotationDiscoveryCallback callback);
}