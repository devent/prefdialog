package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.EventListenerList;

import com.globalscalingsoftware.prefdialog.Validator;

class ValidatingTextField<TextFieldType extends JTextField> {

	private final EventListenerList listenerList;
	private final Border oldBorder;
	private final Border highlighBorder;
	private final Border normalBorder;

	@SuppressWarnings("rawtypes")
	private Validator validator;

	private Object value;
	private final TextFieldType field;

	public ValidatingTextField(TextFieldType field) {
		this.field = field;
		listenerList = new EventListenerList();
		oldBorder = field.getBorder();
		highlighBorder = new LineBorder(Color.red, 1, true);
		normalBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);

		setupListeners();
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

			@Override
			public void focusGained(FocusEvent e) {
				selectAllText();
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
		field.setBorder(BorderFactory.createCompoundBorder(highlighBorder,
				oldBorder));
	}

}
