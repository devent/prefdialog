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
package com.anrisoftware.prefdialog.panel.inputfields.button;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.JButton;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.annotations.ButtonGroup;
import com.anrisoftware.prefdialog.annotations.HorizontalPositions;
import com.anrisoftware.prefdialog.panel.inputfields.button.LoggerFactory.Logger;
import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the {@link ButtonGroupPanel} as the managed component.
 * 
 * The additional attributes are:
 * <ul>
 * <li>horizontalPosition</li>
 * </ul>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class ButtonGroupFieldHandler extends
		AbstractLabelFieldHandler<ButtonGroupPanel> {

	/**
	 * The name prefix for the button.
	 */
	public static final String BUTTON = "button";

	/**
	 * The {@link Logger} for the button field.
	 */
	private Logger log;

	/**
	 * Sets the parameter of the {@link ButtonGroupPanel}.
	 * 
	 * @param panel
	 *            the {@link ButtonGroupPanel}.
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
	 */
	@Inject
	ButtonGroupFieldHandler(ButtonGroupPanel panel,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(parentObject, value, field, ButtonGroup.class, panel);
	}

	/**
	 * Sets the horizontal position of the buttons.
	 */
	@Override
	public FieldHandler<ButtonGroupPanel> setup() {
		FieldHandler<ButtonGroupPanel> handler = super.setup();
		setupHorizontalPosition();
		return handler;
	}

	private void setupHorizontalPosition() {
		Annotation a = getField().getAnnotation(ButtonGroup.class);
		HorizontalPositions position = getReflectionToolbox()
				.invokeMethodWithReturnType("horizontalPosition",
						HorizontalPositions.class, a);
		setHorizontalPosition(position);
	}

	public void setHorizontalPosition(HorizontalPositions position) {
		getComponent().setHorizontalPosition(position);
		log.setHorizontalPosition(position, this);
	}

	/**
	 * Returns the {@link JButton} with a given index.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             if the index is out of range (
	 *             <tt>index &lt; 0 || index &gt;= count of buttons</tt>)
	 */
	public JButton getButton(int index) {
		return getComponent().getButton(index);
	}

	/**
	 * Injects the button field {@link LoggerFactory}.
	 */
	@Inject
	public void setButtonFieldLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(ButtonGroupFieldHandler.class);
	}
}
