/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.internal.reflection;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.annotations.Checkbox;
import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.FileChooser;
import com.globalscalingsoftware.prefdialog.annotations.FormattedTextField;
import com.globalscalingsoftware.prefdialog.annotations.Group;
import com.globalscalingsoftware.prefdialog.annotations.RadioButton;
import com.globalscalingsoftware.prefdialog.annotations.Slider;
import com.globalscalingsoftware.prefdialog.annotations.TextField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.button.CheckboxFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.button.RadioButtonFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.ChildFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.child.GroupFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.combobox.ComboBoxInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.filechooser.FileChooserFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield.FormattedTextFieldInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.formattedtextfield.TextFieldInputField;
import com.globalscalingsoftware.prefdialog.internal.inputfield.slider.SliderFieldHandler;

public class FieldsAnnotationFilter extends AbstractAnnotationFilter {

	private static List<Class<?>> fieldsAnnotations;
	private static Map<Class<? extends Annotation>, Class<? extends FieldHandler<?>>> inputFieldImplementations;

	static {
		fieldsAnnotations = new ArrayList<Class<?>>();
		fieldsAnnotations.add(Checkbox.class);
		fieldsAnnotations.add(FormattedTextField.class);
		fieldsAnnotations.add(TextField.class);
		fieldsAnnotations.add(RadioButton.class);
		fieldsAnnotations.add(ComboBox.class);
		fieldsAnnotations.add(Slider.class);
		fieldsAnnotations.add(Group.class);
		fieldsAnnotations.add(Child.class);
		fieldsAnnotations.add(FileChooser.class);

		inputFieldImplementations = new HashMap<Class<? extends Annotation>, Class<? extends FieldHandler<?>>>();
		inputFieldImplementations.put(Checkbox.class,
				CheckboxFieldHandler.class);
		inputFieldImplementations.put(FormattedTextField.class,
				FormattedTextFieldInputField.class);
		inputFieldImplementations.put(TextField.class,
				TextFieldInputField.class);
		inputFieldImplementations.put(RadioButton.class,
				RadioButtonFieldHandler.class);
		inputFieldImplementations.put(ComboBox.class, ComboBoxInputField.class);
		inputFieldImplementations.put(Slider.class, SliderFieldHandler.class);
		inputFieldImplementations.put(Group.class, GroupFieldHandler.class);
		inputFieldImplementations.put(Child.class, ChildFieldHandler.class);
		inputFieldImplementations.put(FileChooser.class,
				FileChooserFieldHandler.class);
	}

	FieldsAnnotationFilter() {
		super(fieldsAnnotations);
	}

	public Map<Class<? extends Annotation>, Class<? extends FieldHandler<?>>> getFieldsImplementations() {
		return inputFieldImplementations;
	}

}
