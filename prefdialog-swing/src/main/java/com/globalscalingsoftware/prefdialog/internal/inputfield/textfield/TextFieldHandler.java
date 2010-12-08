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
package com.globalscalingsoftware.prefdialog.internal.inputfield.textfield;

import java.lang.reflect.Field;

import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class TextFieldHandler extends AbstractTextFieldHandler {

	@Inject
	TextFieldHandler(ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(reflectionToolbox, parentObject, value, field, TextField.class,
				new ValidatingTextField<JTextField>(new JTextField()));
	}

}
