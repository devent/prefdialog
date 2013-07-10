/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.radiobutton;

import java.beans.PropertyVetoException;

import javax.inject.Inject;
import javax.swing.JRadioButton;

import com.anrisoftware.prefdialog.fields.fieldbutton.AbstractFieldButtonField;
import com.google.inject.assistedinject.Assisted;

/**
 * Radio button field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class RadioButtonField extends AbstractFieldButtonField<JRadioButton> {

	private final RadioButtonFieldLogger log;

	/**
	 * @see RadioButtonFieldFactory#create(Object, String)
	 */
	@Inject
	RadioButtonField(RadioButtonFieldLogger logger,
			@Assisted Object parentObject, @Assisted String fieldName) {
		super(new JRadioButton(), parentObject, fieldName);
		this.log = logger;
	}

	/**
	 * Sets the boolean value for the radio button.
	 * 
	 * @param value
	 *            the new boolean value. {@code true} for a checked radio button
	 *            and {@code false} for unchecked.
	 * 
	 * @throws PropertyVetoException
	 *             if the user input is not valid.
	 * 
	 * @throws IllegalArgumentException
	 *             if the value is not a boolean value.
	 */
	@Override
	public void setValue(Object value) throws PropertyVetoException {
		super.setValue(value);
		log.checkValue(this, value);
		getComponent().setSelected((Boolean) value);
	}
}
