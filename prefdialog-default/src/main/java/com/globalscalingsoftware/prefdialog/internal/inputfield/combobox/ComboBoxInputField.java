package com.globalscalingsoftware.prefdialog.internal.inputfield.combobox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractInputField;

public class ComboBoxInputField extends AbstractInputField<ComboBoxPanel> {

	public ComboBoxInputField(IReflectionToolbox reflectionToolbox,
			Object parentObject, Object value, Field field) {
		super(reflectionToolbox, parentObject, value, field,
				new ComboBoxPanel());

		Object values = getValues(reflectionToolbox, parentObject, field);
		getComponent().setValues(values);
		getComponent().setValue(value);
	}

	private Object getValues(IReflectionToolbox reflectionToolbox,
			Object parentObject, Field field) {
		Annotation a = field.getAnnotation(ComboBox.class);
		String comboBoxName = reflectionToolbox.invokeMethodWithReturnType(
				"value", String.class, a);

		Object values = reflectionToolbox.searchObjectWithAnnotationValueIn(
				parentObject, ComboBoxElements.class, comboBoxName,
				String.class);
		return values;
	}

	@Override
	public Object getValue() {
		return getComponent().getValue();
	}
}
