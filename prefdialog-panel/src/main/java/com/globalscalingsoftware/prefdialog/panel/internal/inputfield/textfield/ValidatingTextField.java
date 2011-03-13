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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.textfield;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.EventListenerList;

import com.globalscalingsoftware.prefdialog.validators.Validator;

public class ValidatingTextField<TextFieldType extends JTextField> {

	private final EventListenerList listenerList;
	private final Border oldBorder;
	private final Border highlighBorder;

	@SuppressWarnings("rawtypes")
	private Validator validator;

	private Object value;
	private final TextFieldType field;

	public ValidatingTextField(TextFieldType field) {
		this.field = field;
		listenerList = new EventListenerList();
		oldBorder = field.getBorder();
		highlighBorder = new LineBorder(Color.red, 1, false);

		setupTextField();
		setupListeners();
	}

	private void setupTextField() {
		int height = field.getPreferredSize().height;
		field.setPreferredSize(new Dimension(200, height));
	}

	public void setValidator(Validator<?> validator) {
		this.validator = validator;
	}

	public TextFieldType getField() {
		return field;
	}

	private void setupListeners() {
		field.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				validateInput();
			}
		});
		field.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				restoreValueIfInvalid();
			}

		});
	}

	private void restoreValueIfInvalid() {
		if (isNotValidInput()) {
			setValue(value);
			if (isNotValidInput()) {
				highlighField();
				fireValidChanged(false);
			} else {
				normalField();
				fireValidChanged(true);
			}
		}
	}

	protected void setValue(Object value) {
		this.value = value;
		String string = value == null ? "" : value.toString();
		field.setText(string);
	}

	public void addValidListener(ValidListener l) {
		listenerList.add(ValidListener.class, l);
		validateInput();
	}

	private void fireValidChanged(boolean editValid) {
		for (ValidListener l : listenerList.getListeners(ValidListener.class)) {
			l.validChanged(new ValidEvent(this, editValid));
		}
	}

	private void validateInput() {
		if (isNotValidInput()) {
			highlighField();
			fireValidChanged(false);
		} else {
			normalField();
			fireValidChanged(true);
		}
	}

	private void normalField() {
		field.setBorder(oldBorder);
	}

	private boolean isNotValidInput() {
		try {
			commitEdit();
		} catch (ParseException e) {
			return true;
		}

		boolean valueInvalid = isValueInvalid();
		boolean b = !isEditValid() || valueInvalid;
		return b;
	}

	@SuppressWarnings("unchecked")
	private boolean isValueInvalid() {
		return validator == null ? false : !validator.isValid(getValue());
	}

	protected Object getValue() {
		return field.getText();
	}

	protected boolean isEditValid() {
		return true;
	}

	protected void commitEdit() throws ParseException {
	}

	private void highlighField() {
		field.setBorder(highlighBorder);
	}

}
