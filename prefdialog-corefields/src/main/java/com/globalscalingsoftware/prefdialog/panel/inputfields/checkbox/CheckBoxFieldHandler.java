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
package com.globalscalingsoftware.prefdialog.panel.inputfields.checkbox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.swingutils.AbstractLabelFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the {@link CheckBoxPanel} as the managed component.
 * 
 * The additional attributes are:
 * <ul>
 * <li>text</li>
 * </ul>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class CheckBoxFieldHandler extends AbstractLabelFieldHandler<CheckBoxPanel> {

	private LoggerFactory.Logger log;

	/**
	 * Sets the parameter of the {@link CheckBoxPanel}.
	 * 
	 * @param panel
	 *            the {@link CheckBoxPanel}.
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
	CheckBoxFieldHandler(CheckBoxPanel panel,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(parentObject, value, field, Checkbox.class, panel);
	}

	/**
	 * Sets the text of the checkbox.
	 */
	@Override
	public FieldHandler<CheckBoxPanel> setup() {
		setupText();
		return super.setup();
	}

	private void setupText() {
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		Annotation a = getField().getAnnotation(annotationClass);
		String text = getReflectionToolbox().invokeMethodWithReturnType("text",
				String.class, a);
		getComponent().setText(text);
		log.setText(text, this);
	}

	/**
	 * Injects the checkbox field {@link LoggerFactory}.
	 */
	@Inject
	public void setCheckBoxFieldLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(CheckBoxFieldHandler.class);
	}
}
