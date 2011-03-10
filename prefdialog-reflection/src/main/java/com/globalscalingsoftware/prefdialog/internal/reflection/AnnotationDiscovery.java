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
package com.globalscalingsoftware.prefdialog.internal.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.annotations.Stateless;
import com.google.inject.Inject;

@Stateless
public class AnnotationDiscovery {

	private class DiscoveryWorker {

		private final Object object;
		private final AnnotationDiscoveryCallback callback;

		public DiscoveryWorker(Object object,
				AnnotationDiscoveryCallback callback) {
			this.object = object;
			this.callback = callback;
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
				informCallbackIfFilterAccepts(field, annotation);
			}
		}

		private void informCallbackIfFilterAccepts(Field field,
				Annotation annotation) {
			if (filter.accept(annotation)) {
				informCallback(field, annotation);
			}
		}

		private void informCallback(Field field, Annotation annotation) {
			Object value = reflectionToolbox.getValueFrom(field, object);
			callback.fieldAnnotationDiscovered(field, value, annotation);
		}

	}

	private final ReflectionToolbox reflectionToolbox;

	private final AnnotationFilter filter;

	@Inject
	AnnotationDiscovery(AnnotationFilter filter,
			ReflectionToolbox reflectionToolbox) {
		this.filter = filter;
		this.reflectionToolbox = reflectionToolbox;
	}

	public void discoverAnnotations(Object object,
			AnnotationDiscoveryCallback callback) {
		if (object == null) {
			return;
		}
		new DiscoveryWorker(object, callback).discoverFields();
	}

}
