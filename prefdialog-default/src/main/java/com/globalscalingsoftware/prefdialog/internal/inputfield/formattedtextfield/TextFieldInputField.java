package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.lang.reflect.Field;

import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.annotations.TextField;

public class TextFieldInputField extends AbstractTextField implements
		IInputField {

	public TextFieldInputField(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, TextField.class,
				new ValidatingTextField<JTextField>(new JTextField()));
	}

}
