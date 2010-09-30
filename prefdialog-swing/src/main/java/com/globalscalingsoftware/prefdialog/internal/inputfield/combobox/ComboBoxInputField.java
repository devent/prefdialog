package com.globalscalingsoftware.prefdialog.internal.inputfield.combobox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.fields.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;

public class ComboBoxInputField extends AbstractDefaultFieldHandler<ComboBoxPanel> {

	public ComboBoxInputField(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, ComboBox.class, new ComboBoxPanel());
	}

	@Override
	public void setup() {
		super.setup();
		Object parentObject = getParentObject();
		Field field = getField();
		Object values = getValuesFromAnnotationIn(parentObject, field);
		getComponent().setValues(values);
	}

	private Object getValuesFromAnnotationIn(Object parentObject, Field field) {
		Annotation a = field.getAnnotation(ComboBox.class);
		String comboBoxName = getReflectionToolbox()
				.invokeMethodWithReturnType("value", String.class, a);

		Object values = getReflectionToolbox()
				.searchObjectWithAnnotationValueIn(parentObject,
						ComboBoxElements.class, comboBoxName, String.class);
		return values;
	}

}
