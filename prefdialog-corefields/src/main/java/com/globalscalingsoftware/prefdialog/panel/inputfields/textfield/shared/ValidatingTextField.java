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
package com.globalscalingsoftware.prefdialog.panel.inputfields.textfield.shared;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.EventListenerList;

import com.globalscalingsoftware.prefdialog.validators.Validator;

/**
 * Will test the input of a {@link JTextField} and mark the field and show a
 * tool-tip text if the input is not valid.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public class ValidatingTextField<TextFieldType extends JTextField> {

	private final EventListenerList listenerList;

	private final Border oldBorder;

	private final Border highlighBorder;

	@SuppressWarnings("rawtypes")
	private Validator validator;

	private Object value;

	private final TextFieldType field;

	/**
	 * Sets the {@link JTextField} for with the input will be validated.
	 */
	public ValidatingTextField(TextFieldType field) {
		this.field = field;
		this.listenerList = new EventListenerList();
		this.oldBorder = field.getBorder();
		this.highlighBorder = new LineBorder(Color.red, 1, false);

		setupTextField();
		setupListeners();
	}

	private void setupTextField() {
		int height = field.getPreferredSize().height;
		field.setPreferredSize(new Dimension(200, height));
	}

	private void setupListeners() {
		field.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				validateInput();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				validateInput();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		field.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				restoreValueIfInvalid();
			}

		});
	}

	private void validateInput() {
		if (!isValidInput()) {
			highlighField();
			fireValidChanged(false);
		} else {
			normalField();
			fireValidChanged(true);
		}
	}

	private void restoreValueIfInvalid() {
		if (!isValidInput()) {
			setValue(value);
			if (!isValidInput()) {
				highlighField();
				fireValidChanged(false);
			} else {
				normalField();
				fireValidChanged(true);
			}
		}
	}

	private void highlighField() {
		field.setBorder(highlighBorder);
	}

	private void normalField() {
		field.setBorder(oldBorder);
	}

	private void fireValidChanged(boolean editValid) {
		for (ValidListener l : listenerList.getListeners(ValidListener.class)) {
			l.validChanged(new ValidEvent(this, editValid));
		}
	}

	/**
	 * Sets the {@link Validator} for this field.
	 */
	public void setValidator(Validator<?> validator) {
		this.validator = validator;
	}

	/**
	 * Returns the {@link JTextField} that is tested.
	 */
	public TextFieldType getField() {
		return field;
	}

	/**
	 * Adds the {@link ValidListener} that is called if the input is tested.
	 */
	public void addValidListener(ValidListener l) {
		listenerList.add(ValidListener.class, l);
		validateInput();
	}

	private boolean isValidInput() {
		try {
			commitEdit();
		} catch (ParseException e) {
			return false;
		}

		boolean valueValid = isValueValid();
		boolean b = isEditValid() && valueValid;
		return b;
	}

	/**
	 * Commits the entered text for validation.
	 */
	protected void commitEdit() throws ParseException {
	}

	@SuppressWarnings("unchecked")
	private boolean isValueValid() {
		return validator == null ? true : validator.isValid(getValue());
	}

	/**
	 * Test if the entered text is valid.
	 */
	protected boolean isEditValid() {
		return true;
	}

	/**
	 * Sets the value to the field.
	 */
	protected void setValue(Object value) {
		this.value = value;
		String string = value == null ? "" : value.toString();
		field.setText(string);
	}

	/**
	 * Returns the value of the field.
	 */
	protected Object getValue() {
		return field.getText();
	}

}
