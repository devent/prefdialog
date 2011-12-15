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
package com.anrisoftware.prefdialog.panel.inputfields.textfield.shared;

/**
 * Contains information when the input validation of the text field was changed.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ValidEvent {

	private final ValidatingTextField<?> textField;

	private final boolean editValid;

	/**
	 * Sets the information for later retrieval.
	 * 
	 * @param textField
	 *            the {@link ValidatingTextField} which input was validated.
	 * 
	 * @param editValid
	 *            if the input is valid or not.
	 */
	public ValidEvent(ValidatingTextField<?> textField, boolean editValid) {
		this.textField = textField;
		this.editValid = editValid;
	}

	/**
	 * Returns the {@link ValidatingTextField} which input was validated.
	 */
	public ValidatingTextField<?> getTextField() {
		return textField;
	}

	/**
	 * Returns if the input is valid or not.
	 */
	public boolean isEditValid() {
		return editValid;
	}

}
