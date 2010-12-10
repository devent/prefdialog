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

import com.globalscalingsoftware.prefdialog.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

public abstract class AbstractChildFieldHandler<ComponentType extends ChildComponent>
		extends AbstractDefaultFieldHandler<ComponentType> implements
		ChildFieldHandler {

	private final List<FieldHandler<?>> fieldHandlers;

	public AbstractChildFieldHandler(ReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field,
			Class<? extends Annotation> annotationClass, ComponentType component) {
		super(reflectionToolbox, parentObject, value, field, annotationClass,
				component);
		this.fieldHandlers = new ArrayList<FieldHandler<?>>();
	}

	@Override
	public void setup() {
		super.setup();
		setupActions();
	}

	public void addFieldHandler(FieldHandler<?> fieldHandler) {
		getComponent().addField(fieldHandler);
		fieldHandlers.add(fieldHandler);
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

	public void applyInput() {
		applyInput(getComponentValue());
	}

	@Override
	public void applyInput(Object parent) {
		for (FieldHandler<?> inputField : fieldHandlers) {
			inputField.applyInput(parent);
		}
	}

	public void restoreInput() {
		restoreInput(getComponentValue());
	}

	@Override
	public void restoreInput(Object parent) {
		for (FieldHandler<?> inputField : fieldHandlers) {
			inputField.restoreInput(parent);
		}
	}

	public void setApplyAction(Action a) {
		getComponent().setApplyAction(a);
	}

	public void setRestoreAction(Action a) {
		getComponent().setRestoreAction(a);
	}

	@Override
	public void setButtonsTransparent(boolean transparent) {
		getComponent().setButtonsTransparent(transparent);
	}
}