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
package com.globalscalingsoftware.prefdialog.internal.inputfield.textfield.formattedtextfield;

import java.text.ParseException;

import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.internal.inputfield.textfield.ValidatingTextField;

public class ValidatingFormattedTextField extends
		ValidatingTextField<JFormattedTextField> {

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
