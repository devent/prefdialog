/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel.inputfields.child;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.anrisoftware.prefdialog.FieldComponent;
import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.annotations.Group;
import com.anrisoftware.prefdialog.swingutils.AbstractDefaultFieldHandler;
import com.google.inject.Inject;

/**
 * Implements combined behavior for {@link Child} and {@link Group} fields. The
 * child-field contains the fields from the value.
 * 
 * @param <ComponentType>
 *            the type of the underlying {@link ChildComponent}. For example a
 *            {@link JPanel}.
 * 
 * @since 2.1
 * @see AbstractDefaultFieldHandler
 * @see ChildComponent
 */
public abstract class AbstractChildFieldHandler<ComponentType extends ChildComponent>
		extends AbstractDefaultFieldHandler<ComponentType> {

	private LoggerFactory.Logger log;

	private final List<FieldHandler<?>> fieldHandlers;

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
	protected AbstractChildFieldHandler(Object parentObject, Object value,
			Field field, Class<? extends Annotation> annotationClass,
			ComponentType component) {
		super(parentObject, value, field, annotationClass, component);
		this.fieldHandlers = new ArrayList<FieldHandler<?>>();
	}

	@Override
	public FieldHandler<ComponentType> setup() {
		setupTitle();
		return super.setup();
	}

	private void setupTitle() {
		String title = getReflectionToolbox().valueFromA(getField(), "title",
				String.class, getAnnotationClass());
		title = defaultTitle(title);
		setComponentTitle(title);
	}

	/**
	 * Injects the child field {@link LoggerFactory}.
	 */
	@Inject
	public void setChildFieldLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(AbstractChildFieldHandler.class);
	}

	/**
	 * Apply the input of all fields.
	 */
	public void applyInput() {
		applyInput(getComponentValue());
	}

	@Override
	public void applyInput(Object parent) {
		for (FieldHandler<?> inputField : fieldHandlers) {
			inputField.applyInput(parent);
		}
	}

	/**
	 * Restore the input of all fields.
	 */
	public void restoreInput() {
		restoreInput(getComponentValue());
	}

	@Override
	public void restoreInput(Object parent) {
		for (FieldHandler<?> inputField : fieldHandlers) {
			inputField.restoreInput(parent);
		}
	}

	@Override
	public void addFieldHandler(FieldHandler<?> fieldHandler) {
		getComponent().addField(fieldHandler);
		fieldHandlers.add(fieldHandler);
		log.fieldHandlerAdded(fieldHandler, this);
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

	/**
	 * Updates the component tree UI beginning with the child component.
	 * 
	 * @see SwingUtilities#updateComponentTreeUI(java.awt.Component)
	 */
	public void updateUI() {
		SwingUtilities.updateComponentTreeUI(getComponent().getAWTComponent());
	}

}