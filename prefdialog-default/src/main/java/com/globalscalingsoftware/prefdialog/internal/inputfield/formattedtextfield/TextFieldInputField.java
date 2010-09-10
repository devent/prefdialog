package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.lang.reflect.Field;

import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;

public class TextFieldInputField extends AbstractTextField implements
		IInputField {

	public TextFieldInputField(IReflectionToolbox reflectionToolbox,
			Object value, Field field) {
		super(reflectionToolbox, value, field,
				new ValidatingTextField<JTextField>(new JTextField()));
	}

}
