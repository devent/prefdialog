package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class CheckBoxPanel extends JPanel {

	private static class Action extends AbstractAction {

		public Action(String fieldName, Boolean value) {
			super(fieldName);
			putValue(SELECTED_KEY, value);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	private final JCheckBox checkBox;

	private final Action action;

	public CheckBoxPanel(String fieldName, Boolean value) {
		checkBox = new JCheckBox();
		action = new Action(fieldName, value);
		setupPanel();
		setupCheckbox();
	}

	private void setupCheckbox() {
		checkBox.setAction(action);
	}

	private void setupPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(checkBox);
	}

	public boolean isSelected() {
		return checkBox.isSelected();
	}
}
