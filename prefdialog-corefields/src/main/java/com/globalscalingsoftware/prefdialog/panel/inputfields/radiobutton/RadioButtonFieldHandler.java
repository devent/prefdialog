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
package com.globalscalingsoftware.prefdialog.panel.inputfields.radiobutton;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.swingutils.AbstractLabelFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the {@link RadioButtonsPanel} as the managed component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class RadioButtonFieldHandler extends
		AbstractLabelFieldHandler<RadioButtonsPanel> {

	private final Object value;

	private LoggerFactory.Logger log;

	/**
	 * Sets the parameter of the {@link RadioButtonsPanel}.
	 * 
	 * @param panel
	 *            the {@link RadioButtonsPanel}.
	 * 
	 * @param parentObject
	 *            the {@link Object} where the field is defined.
	 * 
	 * @param value
	 *            the value of the field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 */
	@Inject
	RadioButtonFieldHandler(RadioButtonsPanel panel,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(parentObject, value, field, RadioButton.class, panel);
		this.value = value;
	}

	/**
	 * Sets the columns and the value.
	 */
	@Override
	public FieldHandler<RadioButtonsPanel> setup() {
		setupColumns();
		setupValue(value);
		return super.setup();
	}

	private void setupColumns() {
		Class<? extends Enum<?>> valueClass = getValueClass(value);
		Enum<?>[] enumFields = getEnumFields(valueClass);
		int columns = getReflectionToolbox().getColumns(getField());
		int rows = enumFields.length;
		log.setColumns(columns, this);
		log.setRows(rows, this);
		getComponent().setColumnsRows(columns, rows);
		log.addEnumFields(enumFields, this);
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

	/**
	 * Injects the radio button field {@link LoggerFactory}.
	 */
	@Inject
	public void setRadioButtonFieldLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(RadioButtonFieldHandler.class);
	}
}
