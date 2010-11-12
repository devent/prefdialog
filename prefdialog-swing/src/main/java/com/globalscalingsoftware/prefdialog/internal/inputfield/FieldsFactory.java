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
package com.globalscalingsoftware.prefdialog.internal.inputfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

import com.globalscalingsoftware.annotations.Stateless;
import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.FieldsAnnotationFilter;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.google.inject.Inject;

@Stateless
public class FieldsFactory {

	private final ReflectionToolbox reflectionToolbox;

	private final Map<Class<? extends Annotation>, Class<? extends FieldHandler<?>>> inputFieldImplementations;

	private final FieldsAnnotationFilter annotationFilter;

	@Inject
	FieldsFactory(FieldsAnnotationFilter annotationFilter,
			ReflectionToolbox reflectionToolbox) {
		this.annotationFilter = annotationFilter;
		this.reflectionToolbox = reflectionToolbox;
		this.inputFieldImplementations = annotationFilter
				.getFieldsImplementations();
	}

	public FieldHandler<?> createField(Object parentObject, Field field,
			Object value) {
		Class<? extends FieldHandler<?>> inputFieldClass = getInputFieldClassFrom(field);
		if (inputFieldClass == null) {
			return null;
		} else {
			return createInputField(parentObject, value, field, inputFieldClass);
		}
	}

	private Class<? extends FieldHandler<?>> getInputFieldClassFrom(Field field) {
		Class<? extends Annotation> a = getInputFieldAnnotationFrom(field);
		Class<? extends FieldHandler<?>> c = inputFieldImplementations.get(a);
		return c;
	}

	private Class<? extends Annotation> getInputFieldAnnotationFrom(Field field) {
		Annotation[] annotations = field.getAnnotations();
		for (Annotation a : annotations) {
			if (annotationFilter.accept(a)) {
				return a.annotationType();
			}
		}
		return null;
	}

	private FieldHandler<?> createInputField(Object parentObject, Object value,
			Field field, Class<? extends FieldHandler<?>> inputFieldClass) {
		Class<?>[] parameterTypes = new Class<?>[] { Object.class,
				Object.class, Field.class };
		FieldHandler<?> inputField = reflectionToolbox.newInstance(
				inputFieldClass, parameterTypes, parentObject, value, field);
		return inputField;
	}

}
