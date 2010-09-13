package com.globalscalingsoftware.prefdialog.internal.inputfield.group;

import javax.swing.JPanel;

import com.globalscalingsoftware.prefdialog.internal.inputfield.IComponent;

@SuppressWarnings("serial")
public class GroupPanel extends UiGroupPanel implements IComponent {

	public GroupPanel() {
	}

	@Override
	public void setFieldWidth(double width) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFieldName(String name) {
		getGroupLabel().setText(name);
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPreferencesPanel(JPanel panel) {
		add(panel, "1, 3, 2, 3");
	}

}
