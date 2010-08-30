package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.TextField;

public class InputField {

	private final ReflectionToolbox reflectionToolbox;

	private final Field field;

	private final Object inputField;

	private final Object parentValue;

	InputField(ReflectionToolbox reflectionToolbox, Object inputField,
			Object parentValue, Field field) {
		this.reflectionToolbox = reflectionToolbox;
		this.inputField = inputField;
		this.parentValue = parentValue;
		this.field = field;
	}

	public void applyInput() {
		if (fieldHaveAnnotation(field, FormattedTextField.class)) {
			JFormattedTextField input = (JFormattedTextField) inputField;
			Object value = input.getValue();
			reflectionToolbox.setValueTo(field, parentValue, value);
		} else if (fieldHaveAnnotation(field, TextField.class)) {
			JTextField input = (JTextField) inputField;
			Object value = input.getText();
			reflectionToolbox.setValueTo(field, parentValue, value);
		}
	}

	private boolean fieldHaveAnnotation(Field field,
			Class<? extends Annotation> annotationClass) {
		return field.getAnnotation(annotationClass) != null;
	}
}
