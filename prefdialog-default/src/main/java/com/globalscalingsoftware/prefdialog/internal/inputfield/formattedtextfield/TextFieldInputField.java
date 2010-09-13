package com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield;

import java.lang.reflect.Field;

import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.annotations.TextField;

public class TextFieldInputField extends AbstractTextField implements
		IInputField {

	public TextFieldInputField(IReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field,
			IFieldsFactory fieldsFactory) {
		super(reflectionToolbox, fieldsFactory, parentObject, value, field,
				TextField.class, new ValidatingTextField<JTextField>(
						new JTextField()));
	}

}
