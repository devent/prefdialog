/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-public.
 * 
 * prefdialog-public is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-public is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-public. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.swingutils;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.FieldComponent;
import com.anrisoftware.prefdialog.FieldHandler;
import com.google.inject.Inject;

/**
 * Adds attributes to the getter methods in {@link FieldHandler} and delegates
 * the component getter and setter to an underlying {@link FieldComponent}.
 * 
 * @param <FieldComponentType>
 *            the type of the underlying {@link FieldComponent}.
 * 
 * @see FieldHandler
 * @see FieldComponent
 */
public abstract class AbstractFieldHandler<FieldComponentType extends FieldComponent>
		implements FieldHandler<FieldComponentType> {

	private final Object parentObject;

	private final Object value;

	private final Field field;

	private final Class<? extends Annotation> annotationClass;

	private final FieldComponentType component;

	private LoggerFactory.Logger log;

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
	protected AbstractFieldHandler(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			FieldComponentType component) {
		this.parentObject = parentObject;
		this.value = value;
		this.field = field;
		this.annotationClass = annotationClass;
		this.component = component;
	}

	/**
	 * Sets the component value.
	 */
	@Override
	public FieldHandler<FieldComponentType> setup() {
		setComponentValue(value);
		return this;
	}

	/**
	 * Injects the {@link LoggerFactory}.
	 */
	@Inject
	public void setLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(AbstractFieldHandler.class);
	}

	/**
	 * Returns the {@link FieldComponent} that is manages by this handler.
	 */
	protected FieldComponentType getComponent() {
		return component;
	}

	/**
	 * Returns the {@link Annotation} {@link Class} of the field.
	 */
	protected Class<? extends Annotation> getAnnotationClass() {
		return annotationClass;
	}

	/**
	 * Returns the {@link Field}.
	 */
	protected Field getField() {
		return field;
	}

	/**
	 * Returns the {@link Object} where the field is defined.
	 */
	protected Object getParentObject() {
		return parentObject;
	}

	/**
	 * Returns the value of the field.
	 */
	protected Object getValue() {
		return value;
	}

	@Override
	public boolean isInputValid() {
		return component.isInputValid();
	}

	@Override
	public void setComponentWidth(double width) {
		log.setWidth(width, this);
		component.setWidth(width);
	}

	@Override
	public void setComponentName(String name) {
		log.setName(name, this);
		component.setName(name);
	}

	@Override
	public void setComponentTitle(String title) {
		log.setTitle(title, this);
		component.setTitle(title);
	}

	@Override
	public void setComponentEnabled(boolean enabled) {
		log.setEnabled(enabled, this);
		component.setEnabled(enabled);
	}

	@Override
	public void setComponentValue(Object value) {
		log.setValue(value, this);
		component.setValue(value);
	}

	@Override
	public Object getComponentValue() {
		return component.getValue();
	}

	@Override
	public Component getAWTComponent() {
		return component.getAWTComponent();
	}

	@Override
	public void addFieldHandler(FieldHandler<?> fieldHandler) {
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("parent object", parentObject)
				.append("field", field).append("value", value)
				.append("annotation", annotationClass).toString();
	}
}
