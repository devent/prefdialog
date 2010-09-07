package com.globalscalingsoftware.prefdialog.internal.formattedtextfield;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.EventListenerList;

import com.globalscalingsoftware.prefdialog.IValidator;

@SuppressWarnings("serial")
class ValidatingFormattedTextField extends JFormattedTextField {

	private final EventListenerList listenerList;
	private final Border oldBorder;
	private final Border highlighBorder;
	private final Border normalBorder;
	@SuppressWarnings("rawtypes")
	private final IValidator validator;
	private final Object value;

	public ValidatingFormattedTextField(Object value,
			@SuppressWarnings("rawtypes") final IValidator validator) {
		this.value = value;
		this.validator = validator;
		listenerList = new EventListenerList();
		oldBorder = getBorder();
		highlighBorder = BorderFactory.createLineBorder(Color.red);
		normalBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		setupListeners();
	}

	private void setupListeners() {
		addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				validateInput();
			}
		});
		addFocusListener(new FocusAdapter() {

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

	protected void restoreValueIfInvalid() {
		if (isNotValidInput()) {
			setValue(value);
		}
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
				selectAll();
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
		setBorder(BorderFactory.createCompoundBorder(normalBorder, oldBorder));
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

	private void highlighField() {
		setBorder(BorderFactory.createCompoundBorder(highlighBorder, oldBorder));
	}

}
