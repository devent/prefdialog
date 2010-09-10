package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.globalscalingsoftware.prefdialog.IFieldsFactory;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.IInputFieldsFactory;
import com.globalscalingsoftware.prefdialog.IPreferencePanelAnnotationFilter;
import com.globalscalingsoftware.prefdialog.IReflectionToolbox;
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.button.CheckboxInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.button.RadioButtonInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield.FormattedTextFieldInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield.TextFieldInputField;
import com.google.inject.Inject;

public class FieldsFactory implements IFieldsFactory {

	private final IReflectionToolbox reflectionToolbox;

	private final IInputFieldsFactory inputFieldFactory;

	private final Map<Class<? extends Annotation>, Class<? extends IInputField>> inputFieldImplementations;

	private final IPreferencePanelAnnotationFilter annotationFilter;

	@Inject
	FieldsFactory(IPreferencePanelAnnotationFilter annotationFilter,
			IReflectionToolbox reflectionToolbox,
			IInputFieldsFactory inputFieldFactory) {
		this.annotationFilter = annotationFilter;
		this.reflectionToolbox = reflectionToolbox;
		this.inputFieldFactory = inputFieldFactory;

		inputFieldImplementations = new HashMap<Class<? extends Annotation>, Class<? extends IInputField>>();
		inputFieldImplementations.put(Checkbox.class, CheckboxInputField.class);
		inputFieldImplementations.put(FormattedTextField.class,
				FormattedTextFieldInputField.class);
		inputFieldImplementations.put(TextField.class,
				TextFieldInputField.class);
		inputFieldImplementations.put(RadioButton.class,
				RadioButtonInputField.class);
	}

	@Override
	public IInputField createField(Object parentObject, Field field,
			Object value) {
		Class<? extends IInputField> inputFieldClass = getInputFieldClassFrom(field);
		return createInputField(value, field, inputFieldClass);
	}

	private IInputField createInputField(Object value, Field field,
			Class<? extends IInputField> inputFieldClass) {
		IInputField inputField = inputFieldFactory.create(inputFieldClass,
				reflectionToolbox, value, field);
		return inputField;
	}

	private Class<? extends IInputField> getInputFieldClassFrom(Field field) {
		Class<? extends Annotation> a = getInputFieldAnnotationFrom(field);
		Class<? extends IInputField> c = inputFieldImplementations.get(a);
		return c;
	}

	private Class<? extends Annotation> getInputFieldAnnotationFrom(Field field) {
		Annotation[] annotations = field.getAnnotations();
		for (Annotation a : annotations) {
			if (annotationFilter.accept(a)) {
				return a.annotationType();
			}
		}
		return null;
	}

}
