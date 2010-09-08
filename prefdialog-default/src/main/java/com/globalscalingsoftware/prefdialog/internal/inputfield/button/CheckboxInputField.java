package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import inputfields.Checkbox;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

import com.globalscalingsoftware.prefdialog.IValidator;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class CheckboxInputField extends AbstractInputField<JCheckBox> implements
		Checkbox {

	@SuppressWarnings("serial")
	static class Action extends AbstractAction {

		public Action(String fieldName, Boolean value) {
			super(fieldName);
			putValue(SELECTED_KEY, value);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	private final Action action;

	public CheckboxInputField(Object value, String fieldName, String helpText,
			IValidator<?> validator) {
		super(fieldName, helpText, new JCheckBox());
		action = new Action(fieldName, (Boolean) value);
		getComponent().setAction(action);
	}

	@Override
	public Object getValue() {
		return getComponent().isSelected();
	}

}
