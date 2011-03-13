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
package com.globalscalingsoftware.prefdialog;

import java.awt.Component;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Adds attributes to the getter methods in {@link FieldHandler} and delegates
 * the the component getter and setter to an underlying {@link FieldComponent}.
 * 
 * @param <FieldComponentType>
 *            the type of the underlying {@link FieldComponent}.
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
	private final InputChangedDelegateCallback callback;

	public AbstractFieldHandler(Object parentObject, Object value, Field field,
			Class<? extends Annotation> annotationClass,
			FieldComponentType component) {
		this.parentObject = parentObject;
		this.value = value;
		this.field = field;
		this.annotationClass = annotationClass;
		this.component = component;
		this.callback = new InputChangedDelegateCallback();
		setup();
	}

	private void setup() {
		setupComponentInputChangedCallback();
		setComponentTitle(field.getName());
		setComponentValue(value);
	}

	private void setupComponentInputChangedCallback() {
		component.setInputChangedCallback(new InputChangedCallback() {

			@Override
			public void inputChanged(Object source) {
				AbstractFieldHandler.this.inputChanged();
			}
		});
	}

	/**
	 * Call the {@link InputChangedCallback} after the input has changed.
	 */
	protected void inputChanged() {
		callback.inputChanged(this);
	}

	protected FieldComponentType getComponent() {
		return component;
	}

	protected Class<? extends Annotation> getAnnotationClass() {
		return annotationClass;
	}

	protected Field getField() {
		return field;
	}

	protected Object getParentObject() {
		return parentObject;
	}

	protected Object getValue() {
		return value;
	}

	@Override
	public void setInputChangedCallback(InputChangedCallback callback) {
		this.callback.setDelegateCallback(callback);
	}

	@Override
	public boolean isInputValid() {
		return component.isInputValid();
	}

	@Override
	public void setComponentWidth(double width) {
		component.setWidth(width);
	}

	@Override
	public void setComponentName(String name) {
		component.setName(name);
	}

	@Override
	public void setComponentTitle(String title) {
		component.setTitle(title);
	}

	@Override
	public void setComponentEnabled(boolean enabled) {
		component.setEnabled(enabled);
	}

	@Override
	public void setComponentValue(Object value) {
		component.setValue(value);
		inputChanged();
	}

	@Override
	public Object getComponentValue() {
		return component.getValue();
	}

	@Override
	public Component getAWTComponent() {
		return component.getAWTComponent();
	}

}
