package com.globalscalingsoftware.prefdialog.internal.inputfield.button;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
class CheckBoxPanel extends JPanel {

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

	private final JCheckBox checkBox;

	private final Action action;

	public CheckBoxPanel() {
		checkBox = new JCheckBox();
		action = new Action();
		setupPanel();
		setupCheckbox();
	}

	public void setInputName(String name) {
		action.setName(name);
	}

	public void setSelected(boolean selected) {
		action.setSelected(selected);
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
