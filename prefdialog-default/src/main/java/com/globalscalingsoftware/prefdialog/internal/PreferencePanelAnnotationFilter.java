package com.globalscalingsoftware.prefdialog.internal;

import java.util.Arrays;

import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.Group;
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.TextField;

public class PreferencePanelAnnotationFilter extends AbstractAnnotationFilter {

	PreferencePanelAnnotationFilter() {
		super(Arrays.asList(new Class[] { Checkbox.class,
				FormattedTextField.class, TextField.class, RadioButton.class,
				ComboBox.class, Group.class, Child.class }));
	}

}
