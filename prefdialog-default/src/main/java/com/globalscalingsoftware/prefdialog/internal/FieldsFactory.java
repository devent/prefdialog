package com.globalscalingsoftware.prefdialog.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.globalscalingsoftware.annotations.Stateless;
import com.globalscalingsoftware.prefdialog.IInputField;
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.Group;
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.button.CheckboxInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.button.RadioButtonInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.GroupInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.combobox.ComboBoxInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield.FormattedTextFieldInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield.TextFieldInputField;
import com.google.inject.Inject;

@Stateless
public class FieldsFactory {

	private final InputFieldsFactory inputFieldFactory;

	private final Map<Class<? extends Annotation>, Class<? extends IInputField>> inputFieldImplementations;

	private final PreferencePanelAnnotationFilter annotationFilter;

	@Inject
	FieldsFactory(PreferencePanelAnnotationFilter annotationFilter,
			InputFieldsFactory inputFieldFactory) {
		this.annotationFilter = annotationFilter;
		this.inputFieldFactory = inputFieldFactory;

		inputFieldImplementations = new HashMap<Class<? extends Annotation>, Class<? extends IInputField>>();
		inputFieldImplementations.put(Checkbox.class, CheckboxInputField.class);
		inputFieldImplementations.put(FormattedTextField.class,
				FormattedTextFieldInputField.class);
		inputFieldImplementations.put(TextField.class,
				TextFieldInputField.class);
		inputFieldImplementations.put(RadioButton.class,
				RadioButtonInputField.class);
		inputFieldImplementations.put(ComboBox.class, ComboBoxInputField.class);
		inputFieldImplementations.put(Group.class, GroupInputField.class);
		inputFieldImplementations.put(Child.class, ChildInputField.class);
	}

	public IInputField createField(Object parentObject, Field field,
			Object value) {
		Class<? extends IInputField> inputFieldClass = getInputFieldClassFrom(field);
		if (inputFieldClass == null) {
			return null;
		} else {
			return createInputField(parentObject, value, field, inputFieldClass);
		}
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

	private IInputField createInputField(Object parentObject, Object value,
			Field field, Class<? extends IInputField> inputFieldClass) {
		IInputField inputField = inputFieldFactory.create(inputFieldClass,
				parentObject, value, field);
		return inputField;
	}

}
