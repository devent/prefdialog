package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractFieldPanel;

@SuppressWarnings("serial")
class CheckBoxPanel extends AbstractFieldPanel<JCheckBox> {

	private static class Action extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
		}

		public void setName(String name) {
			putValue(NAME, name);
		}

		public void setSelected(boolean selected) {
			putValue(SELECTED_KEY, selected);
		}
	}

	private final Action action;

	public CheckBoxPanel() {
		super(new JCheckBox());
		action = new Action();
		setupCheckbox();
	}

	private void setupCheckbox() {
		getField().setAction(action);
	}

	@Override
	public void setTitle(String title) {
		action.setName(title);
	}

	@Override
	public void setValue(Object value) {
		action.setSelected((Boolean) value);
	}

	@Override
	public Object getValue() {
		return getField().isSelected();
	}

}
