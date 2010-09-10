package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.lang.reflect.Field;

import javax.swing.JFormattedTextField;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;

public class FormattedTextFieldInputField extends AbstractTextField implements
		IInputField {

	public FormattedTextFieldInputField(IReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field) {
		super(reflectionToolbox, parentObject, value, field,
				new ValidatingFormattedTextField(new JFormattedTextField()));
	}

}
