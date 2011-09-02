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
package com.globalscalingsoftware.prefdialog.swingutils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldComponent;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionToolbox;
import com.globalscalingsoftware.prefdialog.swingutils.SharedSwingLoggerFactory.SharedSwingLogger;

/**
 * Reads the common annotation attributes from the field and sets them to the
 * FieldComponent.
 * 
 * The common attributes are:
 * <ul>
 * <li>name</li>
 * <li>width</li>
 * <li>readonly</li>
 * </ul>
 * 
 * @param <FieldComponentType>
 *            the type of the underlying {@link FieldComponent}.
 * @see AbstractFieldHandler
 * @see FieldComponent
 */
public abstract class AbstractDefaultFieldHandler<FieldComponentType extends FieldComponent>
		extends AbstractFieldHandler<FieldComponentType> {

	protected final ReflectionToolbox reflectionToolbox;

	private final SharedSwingLogger log;

	public AbstractDefaultFieldHandler(SharedSwingLoggerFactory loggerFactory,
			ReflectionToolbox reflectionToolbox, Object parentObject,
			Object value, Field field,
			Class<? extends Annotation> annotationClass,
			FieldComponentType component) {
		super(loggerFactory, parentObject, value, field, annotationClass,
				component);
		this.log = loggerFactory.create(AbstractDefaultFieldHandler.class);
		this.reflectionToolbox = reflectionToolbox;
		setup();
	}

	private void setup() {
		Field field = getField();
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		setupComponentWidth(field, annotationClass);
		setupComponentName(field);
		setupComponentReadOnly(field, annotationClass);
	}

	private void setupComponentReadOnly(Field field,
			Class<? extends Annotation> annotationClass) {
		boolean readonly = getValueFromAnnotationIn("readonly", Boolean.class,
				field, annotationClass);
		setComponentEnabled(!readonly);
	}

	private void setupComponentName(Field field) {
		String name = field.getName();
		setComponentName(name);
	}

	private void setupComponentWidth(Field field,
			Class<? extends Annotation> annotationClass) {
		double width = getValueFromAnnotationIn("width", Double.class, field,
				annotationClass);
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
		log.applyInput(value, this);
		reflectionToolbox.setValueTo(field, parent, value);
	}

	@Override
	public void restoreInput(Object parent) {
		Object value = getValue();
		log.restoreInput(value, this);
		setComponentValue(value);
	}
}
