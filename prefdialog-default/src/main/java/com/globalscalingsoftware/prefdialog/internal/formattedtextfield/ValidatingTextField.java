package com.globalscalingsoftware.prefdialog.internal.formattedtextfield;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.EventListenerList;

import com.globalscalingsoftware.prefdialog.IValidator;

class ValidatingTextField<TextFieldType extends JTextField> {

	private final EventListenerList listenerList;
	private final Border oldBorder;
	private final Border highlighBorder;
	private final Border normalBorder;
	@SuppressWarnings("rawtypes")
	private final IValidator validator;
	private final Object value;
	private final TextFieldType field;

	public ValidatingTextField(Object value,
			@SuppressWarnings("rawtypes") IValidator validator,
			TextFieldType field) {
		this.value = value;
		this.validator = validator;
		this.field = field;

		setValue(value);
		listenerList = new EventListenerList();
		oldBorder = field.getBorder();
		highlighBorder = BorderFactory.createLineBorder(Color.red);
		normalBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		setupListeners();
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

			@Override
			public void focusGained(FocusEvent e) {
				selectAllText();
			}
		});
	}

	private void restoreValueIfInvalid() {
		if (isNotValidInput()) {
			setValue(value);
		}
	}

	protected void setValue(Object value) {
		String string = value == null ? "" : value.toString();
		field.setText(string);
	}

	public void addValidListener(ValidListener l) {
		listenerList.add(ValidListener.class, l);
	}

	private void fireValidChanged(boolean editValid) {
		for (ValidListener l : listenerList.getListeners(ValidListener.class)) {
			l.validChanged(new ValidEvent(this, editValid));
		}
	}

	private void selectAllText() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				field.selectAll();
			}
		});
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
		field.setBorder(BorderFactory.createCompoundBorder(normalBorder,
				oldBorder));
	}

	@SuppressWarnings("unchecked")
	private boolean isNotValidInput() {
		try {
			commitEdit();
		} catch (ParseException e) {
			return true;
		}
		boolean b = !isEditValid() || !validator.isValid(getValue());
		return b;
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
		field.setBorder(BorderFactory.createCompoundBorder(highlighBorder,
				oldBorder));
	}

}
