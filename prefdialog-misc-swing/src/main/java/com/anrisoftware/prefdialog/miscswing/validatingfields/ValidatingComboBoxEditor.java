/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-misc-swing.
 * 
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
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

import java.awt.Color;

import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Decorated the combo-box component depending whether or not the input is
 * valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class ValidatingComboBoxEditor extends BasicComboBoxEditor {

	/**
	 * Decorates the combo-box as a validating combo-box field.
	 * 
	 * @param field
	 *            the {@link JComboBox}.
	 * 
	 * @return the {@link ValidatingComboBoxEditor} editor.
	 */
	public static ValidatingComboBoxEditor decorate(JComboBox<?> field) {
		ValidatingComboBoxEditor editor = new ValidatingComboBoxEditor();
		field.setEditor(editor);
		return editor;
	}

	private ValidatingTextField validating;

	/**
	 * @see BasicComboBoxEditor#BasicComboBoxEditor()
	 */
	public ValidatingComboBoxEditor() {
		super();
	}

	@Override
	protected JTextField createEditorComponent() {
		validating = new ValidatingTextField("", 9);
		validating.setBorder(null);
		return validating;
	}

	/**
	 * @see ValidatingTextField#setVerifier(InputVerifier)
	 */
	public void setVerifier(InputVerifier verifier) {
		validating.setVerifier(verifier);
	}

	/**
	 * @see ValidatingTextField#getVerifier()
	 */
	public InputVerifier getVerifier() {
		return validating.getInputVerifier();
	}

	/**
	 * @see ValidatingTextField#setInvalidBackground(Color)
	 */
	public void setInvalidBackground(Color color) {
		validating.setInvalidBackground(color);
	}

	/**
	 * @see ValidatingTextField#getInvalidBackground()
	 */
	public Color getInvalidBackground() {
		return validating.getInvalidBackground();
	}

	/**
	 * @see ValidatingTextField#setInputValid(boolean)
	 */
	public void setInputValid(boolean valid) {
		validating.setInputValid(valid);
	}

	/**
	 * @see ValidatingTextField#isInputValid()
	 */
	public boolean isInputValid() {
		return validating.isInputValid();
	}

	/**
	 * @see ValidatingTextField#setInvalidText(String)
	 */
	public void setInvalidText(String text) {
		validating.setInvalidText(text);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.toString();
	}
}
