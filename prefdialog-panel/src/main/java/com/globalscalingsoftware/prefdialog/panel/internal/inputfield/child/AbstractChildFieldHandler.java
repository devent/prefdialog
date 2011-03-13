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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.child;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.InputChangedCallback;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.reflection.internal.ReflectionToolbox;

/**
 * Implements combined behavior for {@link Child} fields. Can contain other
 * {@link FieldHandler}.
 * 
 * @param <ComponentType>
 *            the type of the underlying {@link ChildComponent}.
 * @see AbstractDefaultFieldHandler
 * @see ChildComponent
 */
public abstract class AbstractChildFieldHandler<ComponentType extends ChildComponent>
		extends AbstractDefaultFieldHandler<ComponentType> {

	private final Logger l = LoggerFactory
			.getLogger(AbstractChildFieldHandler.class);

	private final List<FieldHandler<?>> fieldHandlers;

	public AbstractChildFieldHandler(ReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field,
			Class<? extends Annotation> annotationClass, ComponentType component) {
		super(reflectionToolbox, parentObject, value, field, annotationClass,
				component);
		this.fieldHandlers = new ArrayList<FieldHandler<?>>();
		setup();
	}

	private void setup() {
		getComponent().setApplyEvent(new Runnable() {

			@Override
			public void run() {
				applyInput();
				getComponent().setApplyButtonEnabled(false);
			}

		});
		getComponent().setRestoreEvent(new Runnable() {

			@Override
			public void run() {
				restoreInput();
				getComponent().setApplyButtonEnabled(false);
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

	public void addFieldHandler(FieldHandler<?> fieldHandler) {
		l.debug("Add new field handler {}.", fieldHandler);
		getComponent().addField(fieldHandler);
		fieldHandlers.add(fieldHandler);
		fieldHandler.setInputChangedCallback(new InputChangedCallback() {

			@Override
			public void inputChanged(Object source) {
				l.debug("Input has changed for field {}.", source);
				AbstractChildFieldHandler.this.inputChanged();
			}
		});
	}

	public void setApplyAction(Action a) {
		getComponent().setApplyAction(a);
	}

	public void setRestoreAction(Action a) {
		getComponent().setRestoreAction(a);
	}

	public void setButtonsTransparent(boolean transparent) {
		getComponent().setButtonsTransparent(transparent);
	}

	@Override
	public boolean isInputValid() {
		for (FieldHandler<?> fieldHandler : fieldHandlers) {
			if (!fieldHandler.isInputValid()) {
				return false;
			}
		}
		return true;
	}
}