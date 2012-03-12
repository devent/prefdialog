/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-reflection.
 * 
 * prefdialog-reflection is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-reflection is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-reflection. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.reflection;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryCallback;
import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryWorker;
import com.anrisoftware.prefdialog.reflection.api.AnnotationFilter;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Search an object's fields for {@link Annotation}s and if an annotation is
 * found it will call the {@link AnnotationDiscoveryCallback} callback. The
 * annotations are defined by a {@link AnnotationFilter}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class AnnotationDiscoveryWorkerImpl implements AnnotationDiscoveryWorker {

	private final ReflectionToolbox reflectionToolbox;

	private final AnnotationDiscoveryCallback callback;

	private final AnnotationFilter filter;

	/**
	 * Injects the needed dependencies.
	 * 
	 * @param reflectionToolbox
	 *            the {@link ReflectionToolbox} to get the value from the field
	 *            where the annotation was found.
	 * 
	 * @param filter
	 *            the {@link AnnotationFilter} to get the right annotation on
	 *            the field.
	 * 
	 * @param callback
	 *            the {@link AnnotationDiscoveryCallback} that is called if the
	 *            annotation was found on the field.
	 */
	@Inject
	AnnotationDiscoveryWorkerImpl(ReflectionToolbox reflectionToolbox,
			@Assisted AnnotationFilter filter,
			@Assisted AnnotationDiscoveryCallback callback) {
		this.reflectionToolbox = reflectionToolbox;
		this.filter = filter;
		this.callback = callback;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws NullPointerException
	 *             if the given object was <code>null</code>.
	 */
	@Override
	public void discoverAnnotations(Object object) {
		checkNotNull(object,
				"The object for which we discover the annotations cannot be null.");
		discoverFields(object);
	}

	private void discoverFields(Object object) {
		Class<? extends Object> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			searchAnnotations(field, object, annotations);
		}
	}

	private void searchAnnotations(Field field, Object object,
			Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			if (filter.accept(annotation)) {
				informCallback(field, object, annotation);
			}
		}
	}

	private void informCallback(Field field, Object object, Annotation a) {
		Object value = reflectionToolbox.getValueFrom(field, object);
		callback.fieldAnnotationDiscovered(field, value, a);
	}

}
