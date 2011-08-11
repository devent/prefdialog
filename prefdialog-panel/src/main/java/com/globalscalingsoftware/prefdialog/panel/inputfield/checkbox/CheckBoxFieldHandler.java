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
package com.globalscalingsoftware.prefdialog.panel.inputfield.checkbox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.panel.inputfield.checkbox.LoggerFactory.Logger;
import com.globalscalingsoftware.prefdialog.panel.inputfield.shared.AbstractLabelFieldHandler;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class CheckBoxFieldHandler extends AbstractLabelFieldHandler<CheckBoxPanel> {

	private final Logger log;

	@Inject
	CheckBoxFieldHandler(LoggerFactory loggerFactory,
			ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(loggerFactory, reflectionToolbox, parentObject, value, field,
				Checkbox.class, new CheckBoxPanel());
		this.log = loggerFactory.create(CheckBoxFieldHandler.class);
		setup();
	}

	private void setup() {
		setupText();
	}

	private void setupText() {
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		Annotation a = getField().getAnnotation(annotationClass);
		String text = reflectionToolbox.invokeMethodWithReturnType("text",
				String.class, a);
		log.setText(text);
		getComponent().setText(text);
	}
}
