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
package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.fields.RadioButton;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;

public class RadioButtonFieldHandler extends
		AbstractDefaultFieldHandler<RadioButtonsPanel> {

	private final Object value;

	public RadioButtonFieldHandler(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, RadioButton.class,
				new RadioButtonsPanel());
		this.value = value;
	}

	@Override
	public void setup() {
		super.setup();
		setupEnumFields(value);
		setupColumns();
	}

	private void setupColumns() {
		int columns = getReflectionToolbox().getColumns(getField());
		getComponent().setColumns(columns);
	}

	private void setupEnumFields(Object value) {
		Class<? extends Enum<?>> valueclass = getValueClass(value);
		addEnumFields(valueclass);
		getComponent().setValue(value);
	}

	private Class<? extends Enum<?>> getValueClass(Object value) {
		@SuppressWarnings("unchecked")
		Class<? extends Enum<?>> valueclass = (Class<? extends Enum<?>>) value
				.getClass();
		return valueclass;
	}

	private void addEnumFields(Class<? extends Enum<?>> enumClass) {
		for (Object constant : enumClass.getEnumConstants()) {
			getComponent().addRadioButton(constant, constant.toString());
		}
	}

}
