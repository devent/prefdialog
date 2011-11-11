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
import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionToolbox;
import com.globalscalingsoftware.prefdialog.swingutils.LoggerFactory.Logger;
import com.google.inject.Inject;

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

	private Logger log;

	private ReflectionToolbox reflectionToolbox;

	/**
	 * Sets the parameter of the {@link FieldHandler}.
	 * 
	 * @param parentObject
	 *            the {@link Object} where the field is defined.
	 * 
	 * @param value
	 *            the value of the field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 * 
	 * @param annotationClass
	 *            the {@link Annotation} {@link Class} of the field.
	 * 
	 * @param component
	 *            the {@link FieldComponent} that is manages by this handler.
	 */
	protected AbstractDefaultFieldHandler(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			FieldComponentType component) {
		super(parentObject, value, field, annotationClass, component);
	}

	/**
	 * Sets the component width, name and read-only.
	 */
	@Override
	public FieldHandler<FieldComponentType> setup() {
		Field field = getField();
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		setupComponentWidth(field, annotationClass);
		setupComponentName(field);
		setupComponentReadOnly(field, annotationClass);
		return super.setup();
	}

	private void setupComponentReadOnly(Field field,
			Class<? extends Annotation> annotationClass) {
		boolean readonly = valueFromAnnotationInField("readonly",
				Boolean.class, field, annotationClass);
		setComponentEnabled(!readonly);
	}

	private void setupComponentName(Field field) {
		String name = field.getName();
		setComponentName(name);
	}

	private void setupComponentWidth(Field field,
			Class<? extends Annotation> annotationClass) {
		double width = valueFromAnnotationInField("width", Double.class, field,
				annotationClass);
		setComponentWidth(width);
	}

	protected <T> T valueFromAnnotationInField(String name,
			Class<T> returnType, Field field,
			Class<? extends Annotation> annotationClass) {
		Annotation a = field.getAnnotation(annotationClass);
		return reflectionToolbox
				.invokeMethodWithReturnType(name, returnType, a);
	}

	/**
	 * Injects the {@link LoggerFactory}.
	 */
	@Override
	@Inject
	public void setLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(AbstractDefaultFieldHandler.class);
		super.setLoggerFactory(loggerFactory);
	}

	/**
	 * Injects the {@link ReflectionToolbox} that is used to access and modify
	 * fields.
	 */
	@Inject
	public void setReflectionToolbox(ReflectionToolbox reflectionToolbox) {
		this.reflectionToolbox = reflectionToolbox;
	}

	protected ReflectionToolbox getReflectionToolbox() {
		return reflectionToolbox;
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
