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
package com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.formattedtextfield;

import java.text.ParseException;

import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.shared.ValidatingTextField;

/**
 * Test the input of a {@link JFormattedTextField}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ValidatingFormattedTextField extends
		ValidatingTextField<JFormattedTextField> {

	/**
	 * Sets the {@link JFormattedTextField} for with the input will be
	 * validated.
	 */
	public ValidatingFormattedTextField(JFormattedTextField field) {
		super(field);
	}

	@Override
	protected void setValue(Object value) {
		super.setValue(value);
		getField().setValue(value);
	}

	@Override
	protected Object getValue() {
		return getField().getValue();
	}

	@Override
	protected boolean isEditValid() {
		return getField().isEditValid();
	}

	@Override
	protected void commitEdit() throws ParseException {
		getField().commitEdit();
	}

}
