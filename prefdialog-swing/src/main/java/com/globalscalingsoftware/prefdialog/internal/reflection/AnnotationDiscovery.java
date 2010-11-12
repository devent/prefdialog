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

import com.google.inject.Inject;

public class AnnotationDiscovery {

	private final ReflectionToolbox reflectionToolbox;

	@Inject
	AnnotationDiscovery(ReflectionToolbox reflectionToolbox) {
		this.reflectionToolbox = reflectionToolbox;
	}

	public void discover(AbstractAnnotationFilter filter, Object object,
			DiscoveredListener listener) {
		if (object == null) {
			return;
		}
		discoverFields(filter, object, listener);
	}

	private void discoverFields(AbstractAnnotationFilter filter, Object object,
			DiscoveredListener listener) {
		Class<? extends Object> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				informListenerIfFilterAccepts(filter, object, listener, field,
						annotation);
			}
		}
	}

	private void informListenerIfFilterAccepts(AbstractAnnotationFilter filter,
			Object object, DiscoveredListener listener, Field field,
			Annotation annotation) {
		if (!filter.accept(annotation)) {
			return;
		}

		Object value = reflectionToolbox.getValueFrom(field, object);
		listener.fieldAnnotationDiscovered(field, value, annotation);
		// discover(filter, value, listener);
	}

}
