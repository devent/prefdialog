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
package com.globalscalingsoftware.prefdialog.panel.inputfield.child;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.panel.inputfield.shared.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionToolbox;

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

	public void updateUI() {
		SwingUtilities.updateComponentTreeUI(getComponent().getAWTComponent());
	}

}