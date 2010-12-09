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
package com.globalscalingsoftware.prefdialog.internal.inputfield.radiobutton;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class RadioButtonFieldHandler extends
		AbstractDefaultFieldHandler<RadioButtonsPanel> {

	private final Object value;

	@Inject
	RadioButtonFieldHandler(ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(reflectionToolbox, parentObject, value, field, RadioButton.class,
				new RadioButtonsPanel());
		this.value = value;
	}

	@Override
	public void setup() {
		super.setup();
		setupColumns();
		setupValue(value);
	}

	private void setupColumns() {
		Class<? extends Enum<?>> valueClass = getValueClass(value);
		Enum<?>[] enumFields = getEnumFields(valueClass);
		int columns = reflectionToolbox.getColumns(getField());
		int rows = enumFields.length;
		getComponent().setColumnsRows(columns, rows);
		addEnumFields(enumFields);
	}

	private Class<? extends Enum<?>> getValueClass(Object value) {
		@SuppressWarnings("unchecked")
		Class<? extends Enum<?>> valueclass = (Class<? extends Enum<?>>) value
				.getClass();
		return valueclass;
	}

	private Enum<?>[] getEnumFields(Class<? extends Enum<?>> enumClass) {
		return enumClass.getEnumConstants();
	}

	private void addEnumFields(Enum<?>[] enumFields) {
		for (Enum<?> e : enumFields) {
			getComponent().addRadioButton(e, e.toString());
		}
	}

	private void setupValue(Object value) {
		getComponent().setValue(value);
	}

}
