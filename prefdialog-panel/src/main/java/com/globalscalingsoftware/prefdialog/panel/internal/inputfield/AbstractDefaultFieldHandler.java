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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globalscalingsoftware.prefdialog.FieldComponent;
import com.globalscalingsoftware.prefdialog.reflection.internal.ReflectionToolbox;
import com.globalscalingsoftware.prefdialog.swingutils.internal.AbstractFieldHandler;

/**
 * Setups the width, name, title and read only property read from the field
 * annotation. Implements apply and restore input.
 * 
 * @param <FieldComponentType>
 *            the type of the underlying {@link FieldComponent}.
 * @see AbstractFieldHandler
 * @see FieldComponent
 */
public abstract class AbstractDefaultFieldHandler<FieldComponentType extends FieldComponent>
		extends AbstractFieldHandler<FieldComponentType> {

	private final Logger l = LoggerFactory
			.getLogger(AbstractDefaultFieldHandler.class);

	protected final ReflectionToolbox reflectionToolbox;

	public AbstractDefaultFieldHandler(ReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field,
			Class<? extends Annotation> annotationClass,
			FieldComponentType component) {
		super(parentObject, value, field, annotationClass, component);
		this.reflectionToolbox = reflectionToolbox;
		setup();
	}

	private void setup() {
		Field field = getField();
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		setupComponentWidth(field, annotationClass);
		setupComponentName(field);
		setupComponentTitle(field, annotationClass);
		setupComponentReadOnly(field, annotationClass);
	}

	private void setupComponentReadOnly(Field field,
			Class<? extends Annotation> annotationClass) {
		boolean readonly = getValueFromAnnotationIn("readonly", Boolean.class,
				field, annotationClass);
		l.debug("Set the read only to {} for field {}.", readonly, field);
		setComponentEnabled(!readonly);
	}

	private void setupComponentName(Field field) {
		String name = field.getName();
		l.debug("Set the name to {} for field {}.", name, field);
		setComponentName(name);
	}

	private void setupComponentTitle(Field field,
			Class<? extends Annotation> annotationClass) {
		String title = getValueFromAnnotationIn("value", String.class, field,
				annotationClass);
		title = defaultTitleIfNameNotSet(title, field);
		l.debug("Set the title to {} for field {}.", title, field);
		setComponentTitle(title);
	}

	private String defaultTitleIfNameNotSet(String title, Field field) {
		if (title.isEmpty()) {
			title = field.getName();
		}
		return title;
	}

	private void setupComponentWidth(Field field,
			Class<? extends Annotation> annotationClass) {
		double width = getValueFromAnnotationIn("width", Double.class, field,
				annotationClass);
		l.debug("Set the width to {} for field {}.", width, field);
		setComponentWidth(width);
	}

	protected <T> T getValueFromAnnotationIn(String name, Class<T> returnType,
			Field field, Class<? extends Annotation> annotationClass) {
		Annotation a = field.getAnnotation(annotationClass);
		return reflectionToolbox
				.invokeMethodWithReturnType(name, returnType, a);
	}

	@Override
	public void applyInput(Object parent) {
		Object value = getComponentValue();
		Field field = getField();
		l.debug("Apply input and set value to {} for field {}.", value, field);
		reflectionToolbox.setValueTo(field, parent, value);
	}

	@Override
	public void restoreInput(Object parent) {
		Object value = getValue();
		l.debug("Restore value to {} for field", value, parent);
		setComponentValue(value);
	}
}
