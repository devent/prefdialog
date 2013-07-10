/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.validatingfields;

import java.text.ParseException;

import javax.swing.InputVerifier;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 * Using an input verifier to verify the input on focus lost and input enter.
 * Mark the field and show a tool-tip text if the input is not valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ValidatingFormattedTextField extends ValidatingTextField {

	/**
	 * Decorates the specified text field to be validated.
	 * 
	 * @param field
	 *            the {@link JFormattedTextField}.
	 * 
	 * @param verifier
	 *            the {@link InputVerifier} or {@code null} if the verifier is
	 *            set at a later point.
	 * 
	 * @return the {@link ValidatingFormattedTextField}.
	 */
	public static ValidatingFormattedTextField decorate(
			JFormattedTextField field, InputVerifier verifier) {
		return new ValidatingFormattedTextField(field,
				ValidatingTextFieldUi.decorate(field), verifier);
	}

	/**
	 * @see ValidatingTextField#ValidatingTextField(JTextField, ValidatingUI,
	 *      InputVerifier)
	 */
	protected ValidatingFormattedTextField(JFormattedTextField field,
			ValidatingUI fieldUI, InputVerifier verifier) {
		super(field, fieldUI, verifier);
	}

	@Override
	protected boolean verifyField(InputVerifier verifier, JTextField field) {
		JFormattedTextField ftf = (JFormattedTextField) field;
		return commitAndVerify(verifier, ftf);
	}

	private boolean commitAndVerify(InputVerifier verifier,
			JFormattedTextField field) {
		try {
			field.commitEdit();
			return super.verifyField(verifier, field);
		} catch (ParseException e) {
			return false;
		}
	}
}
