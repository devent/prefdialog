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
	 *            the {@link InputVerifier}.
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
		try {
			((JFormattedTextField) field).commitEdit();
			return super.verifyField(verifier, field);
		} catch (ParseException e) {
			return false;
		}
	}
}
