package com.globalscalingsoftware.prefdialog.internal.reflection;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.globalscalingsoftware.prefdialog.InputField;
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

public class FieldsAnnotationFilter extends AbstractAnnotationFilter {

	private static List<Class<?>> fieldsAnnotations;
	private static Map<Class<? extends Annotation>, Class<? extends InputField<?>>> inputFieldImplementations;

	static {
		fieldsAnnotations = new ArrayList<Class<?>>();
		fieldsAnnotations.add(Checkbox.class);
		fieldsAnnotations.add(FormattedTextField.class);
		fieldsAnnotations.add(TextField.class);
		fieldsAnnotations.add(RadioButton.class);
		fieldsAnnotations.add(ComboBox.class);
		fieldsAnnotations.add(Group.class);
		fieldsAnnotations.add(Child.class);

		inputFieldImplementations = new HashMap<Class<? extends Annotation>, Class<? extends InputField<?>>>();
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

	FieldsAnnotationFilter() {
		super(fieldsAnnotations);
	}

	public Map<Class<? extends Annotation>, Class<? extends InputField<?>>> getFieldsImplementations() {
		return inputFieldImplementations;
	}

}
