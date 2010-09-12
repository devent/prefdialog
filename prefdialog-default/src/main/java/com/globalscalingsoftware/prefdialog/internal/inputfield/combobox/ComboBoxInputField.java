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
		super(reflectionToolbox, parentObject, value, field, ComboBox.class,
				new ComboBoxPanel());

		setValuesToComponent();
	}

	private void setValuesToComponent() {
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
