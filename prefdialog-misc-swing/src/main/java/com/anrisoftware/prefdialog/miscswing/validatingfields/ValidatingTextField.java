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
	 *            the {@link InputVerifier}.
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

	/**
	 * @see #decorate(JTextField, InputVerifier)
	 * 
	 * @param fieldUI
	 *            the {@link ValidatingUI} of the text field.
	 */
	protected ValidatingTextField(JTextField field, ValidatingUI fieldUI,
			final InputVerifier verifier) {
		this.fieldUI = fieldUI;
		this.field = field;
		this.verifier = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				JTextField field = ValidatingTextField.this.field;
				boolean valid = verifyField(verifier, field);
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

	protected void verifyField() {
		verifier.shouldYieldFocus(field);
	}

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
