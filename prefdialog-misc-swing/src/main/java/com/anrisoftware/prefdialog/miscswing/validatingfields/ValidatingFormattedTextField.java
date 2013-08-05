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
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.ParseException;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.anrisoftware.prefdialog.miscswing.tooltip.ToolTipShower;

/**
 * Using an input verifier to verify the input on focus lost and input enter.
 * Mark the field and show a tool-tip text if the input is not valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class ValidatingFormattedTextField extends JFormattedTextField {

	private InputVerifier verifier;

	private ActionListener validateAction;

	private InputVerifier parentVerifier;

	private ValidatingComponent validating;

	/**
	 * @see JFormattedTextField#JFormattedTextField()
	 */
	public ValidatingFormattedTextField() {
		super();
		setupField();
	}

	/**
	 * @see JFormattedTextField#JFormattedTextField(AbstractFormatter)
	 */
	public ValidatingFormattedTextField(AbstractFormatter formatter) {
		super(formatter);
		setupField();
	}

	/**
	 * @see JFormattedTextField#JFormattedTextField(AbstractFormatterFactory,
	 *      Object)
	 */
	public ValidatingFormattedTextField(AbstractFormatterFactory factory,
			Object currentValue) {
		super(factory, currentValue);
		setupField();
	}

	/**
	 * @see JFormattedTextField#JFormattedTextField(AbstractFormatterFactory)
	 */
	public ValidatingFormattedTextField(AbstractFormatterFactory factory) {
		super(factory);
		setupField();
	}

	/**
	 * @see JFormattedTextField#JFormattedTextField(Format)
	 */
	public ValidatingFormattedTextField(Format format) {
		super(format);
		setupField();
	}

	/**
	 * @see JFormattedTextField#JFormattedTextField(Object)
	 */
	public ValidatingFormattedTextField(Object value) {
		super(value);
		setupField();
	}

	private void setupField() {
		this.validating = new ValidatingComponent(this, new ToolTipShower(this));
		this.parentVerifier = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				return validating.isValid();
			}
		};
		this.verifier = new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {
				JFormattedTextField field = ValidatingFormattedTextField.this;
				boolean valid = verifyField(parentVerifier, field);
				setValidInAWT(valid);
				return valid;
			}

		};
		this.validateAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				verifyField();
			}

		};
		setInputVerifier(verifier);
		addActionListener(validateAction);
	}

	/**
	 * Verify the field from an external event.
	 */
	protected void verifyField() {
		verifier.shouldYieldFocus(this);
	}

	/**
	 * Verifies the field.
	 * 
	 * @param verifier
	 *            the {@link InputVerifier} that verifiers the input.
	 * 
	 * @param input
	 *            the {@link JTextFormattedField}.
	 * 
	 * @return {@code true} if the input is valid and the focus can be yield.
	 */
	protected boolean verifyField(InputVerifier verifier,
			JFormattedTextField input) {
		try {
			input.commitEdit();
			return verifier.verify(input);
		} catch (ParseException e) {
			return false;
		}
	}

	private void setValidInAWT(final boolean valid) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				setInputValid(valid);
			}
		});
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
	 * Returns the input verifier that validated the input.
	 * 
	 * @return the {@link InputVerifier}.
	 */
	public InputVerifier getVerifier() {
		return parentVerifier;
	}

	/**
	 * @see ValidatingComponent#setInvalidBackground(Color)
	 */
	public void setInvalidBackground(Color color) {
		validating.setInvalidBackground(color);
	}

	/**
	 * @see ValidatingComponent#getInvalidBackground()
	 */
	public Color getInvalidBackground() {
		return validating.getInvalidBackground();
	}

	/**
	 * @see ValidatingComponent#setValid(boolean)
	 */
	public void setInputValid(boolean valid) {
		validating.setValid(valid);
	}

	/**
	 * @see ValidatingComponent#isValid()
	 */
	public boolean isInputValid() {
		return validating.isValid();
	}

	/**
	 * @see ValidatingComponent#setInvalidText(String)
	 */
	public void setInvalidText(String text) {
		validating.setInvalidText(text);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		validating.paint(g);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.toString();
	}
}
