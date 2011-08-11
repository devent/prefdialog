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
package com.globalscalingsoftware.prefdialog.panel.inputfield.radiobutton;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.panel.inputfield.AbstractLabelFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfield.radiobutton.LoggerFactory.Logger;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class RadioButtonFieldHandler extends
		AbstractLabelFieldHandler<RadioButtonsPanel> {

	private final Object value;

	private final Logger log;

	@Inject
	RadioButtonFieldHandler(LoggerFactory loggerFactory,
			ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(reflectionToolbox, parentObject, value, field, RadioButton.class,
				new RadioButtonsPanel());
		this.log = loggerFactory.create(RadioButtonFieldHandler.class);
		this.value = value;
		setup();
	}

	private void setup() {
		setupColumns();
		setupValue(value);
	}

	private void setupColumns() {
		Class<? extends Enum<?>> valueClass = getValueClass(value);
		Enum<?>[] enumFields = getEnumFields(valueClass);
		int columns = reflectionToolbox.getColumns(getField());
		int rows = enumFields.length;
		log.setColumnsRows(getField(), columns, rows);
		getComponent().setColumnsRows(columns, rows);
		log.addEnumFields(getField(), enumFields);
		addEnumFields(enumFields);
	}

	@SuppressWarnings("unchecked")
	private Class<? extends Enum<?>> getValueClass(Object value) {
		return ((Class<? extends Enum<?>>) value.getClass());
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
