package com.globalscalingsoftware.prefdialog.internal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.EventListenerList;

@SuppressWarnings("serial")
class ValidatingFormattedTextField extends JFormattedTextField {

	private final EventListenerList listenerList;
	private final Border oldBorder;
	private final Border highlighBorder;
	private final Border normalBorder;

	public ValidatingFormattedTextField() {
		listenerList = new EventListenerList();
		oldBorder = getBorder();
		highlighBorder = BorderFactory.createLineBorder(Color.red);
		normalBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				validateInput();
			}
		});
		addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				selectAllText();
			}
		});
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				validateInput();
			}
		});
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

	private boolean isNotValidInput() {
		return !isEditValid();
	}

	private void highlighField() {
		setBorder(BorderFactory.createCompoundBorder(highlighBorder, oldBorder));
	}

}
