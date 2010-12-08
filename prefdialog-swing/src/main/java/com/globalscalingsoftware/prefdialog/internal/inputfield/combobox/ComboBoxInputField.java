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
package com.globalscalingsoftware.prefdialog.internal.inputfield.combobox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;

public class ComboBoxInputField extends
		AbstractDefaultFieldHandler<ComboBoxPanel> {

	public ComboBoxInputField(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, ComboBox.class, new ComboBoxPanel());
	}

	@Override
	public void setup() {
		super.setup();
		Object parentObject = getParentObject();
		Field field = getField();
		Object values = getValuesFromAnnotationIn(parentObject, field);
		getComponent().setValues(values);
	}

	private Object getValuesFromAnnotationIn(Object parentObject, Field field) {
		Annotation a = field.getAnnotation(ComboBox.class);
		String comboBoxName = getReflectionToolbox()
				.invokeMethodWithReturnType("value", String.class, a);

		Object values = getReflectionToolbox()
				.searchObjectWithAnnotationValueIn(parentObject,
						ComboBoxElements.class, comboBoxName, String.class);
		return values;
	}

}
