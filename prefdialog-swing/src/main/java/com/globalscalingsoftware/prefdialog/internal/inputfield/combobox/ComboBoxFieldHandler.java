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
package com.globalscalingsoftware.prefdialog.internal.inputfield.combobox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.ComboBoxModel;

import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class ComboBoxFieldHandler extends
		AbstractDefaultFieldHandler<ComboBoxPanel> {

	@Inject
	ComboBoxFieldHandler(ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(reflectionToolbox, parentObject, value, field, ComboBox.class,
				new ComboBoxPanel());
	}

	@Override
	public void setup() {
		super.setup();
		setupElements();
		setupCustomModel();
	}

	private void setupCustomModel() {
		Field field = getField();
		Annotation a = field.getAnnotation(ComboBox.class);
		@SuppressWarnings("unchecked")
		Class<? extends ComboBoxModel> modelClass = getAnnotationField(
				Class.class, a, "model");
		ComboBoxModel model = createModel(modelClass);
		getComponent().setModel(model);
	}

	private ComboBoxModel createModel(Class<? extends ComboBoxModel> modelClass) {
		try {
			return modelClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	private void setupElements() {
		Object values = getValuesFromAnnotationIn();
		if (values != null) {
			getComponent().setValues(values);
		}
	}

	private Object getValuesFromAnnotationIn() {
		Object parentObject = getParentObject();
		Field field = getField();
		Annotation a = field.getAnnotation(ComboBox.class);
		String comboBoxName = getAnnotationField(String.class, a, "elements");

		Object values = reflectionToolbox.searchObjectWithAnnotationValueIn(
				parentObject, ComboBoxElements.class, comboBoxName,
				String.class);
		return values;
	}

	private <T> T getAnnotationField(Class<? extends T> clazz, Annotation a,
			String name) {
		return reflectionToolbox.invokeMethodWithReturnType(name, clazz, a);
	}

}
