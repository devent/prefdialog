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
package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

public abstract class AbstractChildFieldHandler<ComponentType extends IChildComponent>
		extends AbstractDefaultFieldHandler<ComponentType> {

	private FieldsFactory fieldsFactory;

	private final List<FieldHandler<?>> inputFields;

	private Action applyAction;

	private Action restoreAction;

	public AbstractChildFieldHandler(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			ComponentType component) {
		super(parentObject, value, field, annotationClass, component);
		inputFields = new ArrayList<FieldHandler<?>>();
	}

	@Override
	public void setup() {
		super.setup();
		addAllInputFields();
		setupActions();
	}

	private void setupActions() {
		getComponent().setApplyEvent(new Runnable() {

			@Override
			public void run() {
				applyInput();
			}

		});
		getComponent().setRestoreEvent(new Runnable() {

			@Override
			public void run() {
				restoreInput();
			}

		});
	}

	private void addAllInputFields() {
		fillInputFields(inputFields);
		for (FieldHandler<?> inputField : inputFields) {
			setupInputField(inputField);
			getComponent().addField(inputField);
		}
	}

	private void fillInputFields(List<FieldHandler<?>> inputFields) {
		Object parentObject = getComponentValue();
		ReflectionToolbox reflectionToolbox = getReflectionToolbox();
		new AddAllInputFields(reflectionToolbox, fieldsFactory).addAllInto(
				inputFields, parentObject);
	}

	private void setupInputField(FieldHandler<?> inputField) {
		if (inputField instanceof AbstractDefaultFieldHandler) {
			setupDefaultInputField(inputField);
		}
		if (inputField instanceof AbstractChildFieldHandler) {
			setupChildInputField(inputField);
		}
		inputField.setup();
	}

	private void setupChildInputField(FieldHandler<?> inputField) {
		AbstractChildFieldHandler<?> ainputfield = (AbstractChildFieldHandler<?>) inputField;
		ainputfield.setFieldsFactory(fieldsFactory);
		ainputfield.setApplyAction(applyAction);
		ainputfield.setRestoreAction(restoreAction);
	}

	private void setupDefaultInputField(FieldHandler<?> inputField) {
		AbstractDefaultFieldHandler<?> ainputfield = (AbstractDefaultFieldHandler<?>) inputField;
		ReflectionToolbox reflectionToolbox = getReflectionToolbox();
		ainputfield.setReflectionToolbox(reflectionToolbox);
	}

	public void applyInput() {
		applyInput(getComponentValue());
	}

	@Override
	public void applyInput(Object parent) {
		for (FieldHandler<?> inputField : inputFields) {
			inputField.applyInput(parent);
		}
	}

	public void setFieldsFactory(FieldsFactory fieldsFactory) {
		this.fieldsFactory = fieldsFactory;
	}

	public void setApplyAction(Action a) {
		this.applyAction = a;
		getComponent().setApplyAction(a);
	}

	public void setRestoreAction(Action a) {
		this.restoreAction = a;
		getComponent().setRestoreAction(a);
	}

	public void restoreInput() {
		restoreInput(getComponentValue());
	}

	@Override
	public void restoreInput(Object parent) {
		for (FieldHandler<?> inputField : inputFields) {
			inputField.restoreInput(parent);
		}
	}

}