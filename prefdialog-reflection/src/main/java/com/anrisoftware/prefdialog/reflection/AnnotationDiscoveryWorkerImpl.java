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
package com.anrisoftware.prefdialog.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryCallback;
import com.anrisoftware.prefdialog.reflection.api.AnnotationDiscoveryWorker;
import com.anrisoftware.prefdialog.reflection.api.AnnotationFilter;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class AnnotationDiscoveryWorkerImpl implements AnnotationDiscoveryWorker {

	private final ReflectionToolbox reflectionToolbox;

	private final AnnotationDiscoveryCallback callback;

	private final AnnotationFilter filter;

	@Inject
	AnnotationDiscoveryWorkerImpl(ReflectionToolbox reflectionToolbox,
			@Assisted AnnotationFilter filter,
			@Assisted AnnotationDiscoveryCallback callback) {
		this.reflectionToolbox = reflectionToolbox;
		this.filter = filter;
		this.callback = callback;
	}

	/**
	 * Discovers the {@link Annotation annotations} in the given object's
	 * fields.
	 */
	@Override
	public void discoverAnnotations(Object object) {
		checkNull(object);
		discoverFields(object);
	}

	private void checkNull(Object object) {
		if (object == null) {
			throw new NullPointerException(
					"The object for which we discover the annotations cannot be null.");
		}
	}

	public void discoverFields(Object object) {
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
