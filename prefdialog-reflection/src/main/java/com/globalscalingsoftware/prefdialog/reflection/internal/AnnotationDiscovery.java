/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.reflection.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.reflection.AnnotationDiscoveryCallback;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Search an object's fields for {@link Annotation annotations} and if an
 * annotation is found it will call a {@link AnnotationDiscoveryCallback
 * callback}. The annotations are defined by a {@link AnnotationFilter filter}
 * 
 * @see AnnotationFilter
 * @see AnnotationDiscoveryCallback
 * @see Annotation
 */
public class AnnotationDiscovery {

	private final ReflectionToolbox reflectionToolbox;

	private final Object object;

	private final AnnotationDiscoveryCallback callback;

	private final AnnotationFilter filter;

	@Inject
	AnnotationDiscovery(ReflectionToolbox reflectionToolbox,
			@Assisted AnnotationFilter filter, @Assisted Object object,
			@Assisted AnnotationDiscoveryCallback callback) {
		this.reflectionToolbox = reflectionToolbox;
		this.filter = filter;
		this.object = object;
		this.callback = callback;
	}

	/**
	 * Discovers the {@link Annotation annotations} in the given object's
	 * fields.
	 */
	public void discoverAnnotations() {
		checkNull(object);
		discoverFields();
	}

	private void checkNull(Object object) {
		if (object == null) {
			throw new NullPointerException(
					"The object for which we discover the annotations cannot be null.");
		}
	}

	public void discoverFields() {
		Class<? extends Object> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			searchAnnotations(field, annotations);
		}
	}

	private void searchAnnotations(Field field, Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			if (filter.accept(annotation)) {
				informCallback(field, annotation);
			}
		}
	}

	private void informCallback(Field field, Annotation annotation) {
		Object value = reflectionToolbox.getValueFrom(field, object);
		callback.fieldAnnotationDiscovered(field, value, annotation);
	}

}
