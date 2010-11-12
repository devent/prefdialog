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

import com.globalscalingsoftware.prefdialog.AbstractFieldHandler;
import com.globalscalingsoftware.prefdialog.FieldComponent;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

public abstract class AbstractDefaultFieldHandler<FieldComponentType extends FieldComponent>
		extends AbstractFieldHandler<FieldComponentType> {

	private ReflectionToolbox reflectionToolbox;

	public AbstractDefaultFieldHandler(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			FieldComponentType component) {
		super(parentObject, value, field, annotationClass, component);
	}

	@Override
	public void setup() {
		super.setup();
		setupComponent();
	}

	private void setupComponent() {
		Field field = getField();
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		setupComponentWidth(field, annotationClass);
		setupComponentName(field);
		setupComponentTitle(field, annotationClass);
	}

	private void setupComponentName(Field field) {
		String name = field.getName();
		setComponentName(name);
	}

	private void setupComponentTitle(Field field,
			Class<? extends Annotation> annotationClass) {
		String title = getValueFromAnnotationIn("value", String.class, field,
				annotationClass);
		title = defaultTitleIfNameNotSet(title, field);
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
		setComponentWidth(width);
	}

	private <T> T getValueFromAnnotationIn(String name, Class<T> returnType,
			Field field, Class<? extends Annotation> annotationClass) {
		Annotation a = field.getAnnotation(annotationClass);
		return getReflectionToolbox().invokeMethodWithReturnType(name,
				returnType, a);
	}

	public void setReflectionToolbox(ReflectionToolbox reflectionToolbox) {
		this.reflectionToolbox = reflectionToolbox;
	}

	public ReflectionToolbox getReflectionToolbox() {
		return reflectionToolbox;
	}

	@Override
	public void applyInput(Object parent) {
		Object value = getComponentValue();
		Field field = getField();
		getReflectionToolbox().setValueTo(field, parent, value);
	}

	@Override
	public void restoreInput(Object parent) {
		Object value = getValue();
		setComponentValue(value);
	}
}
