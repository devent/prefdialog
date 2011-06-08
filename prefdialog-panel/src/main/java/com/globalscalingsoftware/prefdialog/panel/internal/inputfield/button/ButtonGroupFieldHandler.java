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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.button;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.ButtonGroup;
import com.globalscalingsoftware.prefdialog.annotations.HorizontalPosition;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractLabelFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.button.LoggerFactory.Logger;
import com.globalscalingsoftware.prefdialog.reflection.internal.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class ButtonGroupFieldHandler extends
		AbstractLabelFieldHandler<ButtonGroupPanel> {

	private final Logger log;

	@Inject
	ButtonGroupFieldHandler(LoggerFactory loggerFactory,
			ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(reflectionToolbox, parentObject, value, field, ButtonGroup.class,
				new ButtonGroupPanel());
		this.log = loggerFactory.create(ButtonGroupFieldHandler.class);
		setup();
	}

	private void setup() {
		setupHorizontalPosition();
	}

	private void setupHorizontalPosition() {
		Annotation a = getField().getAnnotation(ButtonGroup.class);
		HorizontalPosition position = reflectionToolbox
				.invokeMethodWithReturnType("horizontalPosition",
						HorizontalPosition.class, a);
		log.setHorizontalPosition(getField(), position);
		getComponent().setHorizontalPosition(position);
	}
}
