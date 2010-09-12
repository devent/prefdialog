package com.globalscalingsoftware.prefdialog.internal.inputfield.group;

import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IPreferencePanelController;
import com.globalscalingsoftware.prefdialog.IPreferencePanelFactory;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.annotations.Group;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class GroupInputField extends AbstractInputField<GroupPanel> {

	public GroupInputField(IReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field,
			IPreferencePanelFactory preferencePanelFactory) {
		super(reflectionToolbox, parentObject, value, field, Group.class,
				new GroupPanel());

		IPreferencePanelController panel = preferencePanelFactory.create(value,
				field);
		panel.setupPanel();
		getComponent().addPreferencesPanel(panel.getPanel());
	}

}
