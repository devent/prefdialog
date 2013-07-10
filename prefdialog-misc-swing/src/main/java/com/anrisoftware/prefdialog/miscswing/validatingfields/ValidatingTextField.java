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

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Using an input verifier to verify the input on focus lost and input enter.
 * Mark the field and show a tool-tip text if the input is not valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ValidatingTextField {

	/**
	 * Decorates the specified text field to be validated.
	 * 
	 * @param field
	 *            the {@link JTextField}.
	 * 
	 * @param verifier
	 *            the {@link InputVerifier} or {@code null} if the verifier is
	 *            set at a later point.
	 * 
	 * @return the {@link ValidatingTextField}.
	 */
	public static ValidatingTextField decorate(JTextField field,
			InputVerifier verifier) {
		return new ValidatingTextField(field,
				ValidatingTextFieldUi.decorate(field), verifier);
	}

	private final JTextField field;

	private final InputVerifier verifier;

	private final ActionListener validateAction;

	private final ValidatingUI fieldUI;

	private InputVerifier parentVerifier;

	/**
	 * @see #decorate(JTextField, InputVerifier)
	 * 
	 * @param fieldUI
	 *            the {@link ValidatingUI} of the text field.
	 */
	protected ValidatingTextField(JTextField field, ValidatingUI fieldUI,
			InputVerifier verifier) {
		this.fieldUI = fieldUI;
		this.field = field;
		this.parentVerifier = verifier;
		this.verifier = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				JTextField field = ValidatingTextField.this.field;
				boolean valid = verifyField(parentVerifier, field);
				setValidAWT(valid);
				return valid;
			}

		};
		this.validateAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				verifyField();
			}

		};
		setupField();
	}

	private void setupField() {
		field.setInputVerifier(verifier);
		field.addActionListener(validateAction);
	}

	/**
	 * Verify the field from an external event.
	 */
	protected void verifyField() {
		verifier.shouldYieldFocus(field);
	}

	/**
	 * Verifies the field.
	 * 
	 * @param verifier
	 *            the {@link InputVerifier} that verifiers the input.
	 * 
	 * @param input
	 *            the {@link JTextField}.
	 * 
	 * @return {@code true} if the input is valid and the focus can be yield.
	 */
	protected boolean verifyField(InputVerifier verifier, JTextField input) {
		return verifier.verify(input);
	}

	private void setValidAWT(final boolean valid) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				setValid(valid);
			}
		});
	}

	/**
	 * Returns the text field that is verified.
	 * 
	 * @return the {@link JTextField}.
	 */
	public JTextField getField() {
		return field;
	}

	/**
	 * Sets the input verifier that validated the input.
	 * 
	 * @param verifier
	 *            the {@link InputVerifier}.
	 */
	public void setVerifier(InputVerifier verifier) {
		this.parentVerifier = verifier;
	}

	/**
	 * @see ValidatingUI#setInvalidBackground(Color)
	 */
	public void setInvalidBackground(Color color) {
		fieldUI.setInvalidBackground(color);
	}

	/**
	 * @see ValidatingUI#getInvalidBackground()
	 */
	public Color getInvalidBackground() {
		return fieldUI.getInvalidBackground();
	}

	/**
	 * @see ValidatingUI#setValid(boolean)
	 */
	public void setValid(boolean valid) {
		fieldUI.setValid(valid);
	}

	/**
	 * @see ValidatingUI#isValid()
	 */
	public boolean isValid() {
		return fieldUI.isValid();
	}

	/**
	 * @see ValidatingUI#setInvalidText(String)
	 */
	public void setInvalidText(String text) {
		fieldUI.setInvalidText(text);
	}

	/**
	 * @see ValidatingUI#getInvalidText()
	 */
	public String getInvalidText() {
		return fieldUI.getInvalidText();
	}

}
