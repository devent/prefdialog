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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.combobox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

import javax.swing.ComboBoxModel;
import javax.swing.ListCellRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractDefaultFieldHandler;
import com.globalscalingsoftware.prefdialog.reflection.internal.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.internal.Nullable;

public class ComboBoxFieldHandler extends
		AbstractDefaultFieldHandler<ComboBoxPanel> {

	private final Logger log = LoggerFactory
			.getLogger(ComboBoxFieldHandler.class);

	@Inject
	ComboBoxFieldHandler(ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") @Nullable Object value, @Assisted Field field) {
		super(reflectionToolbox, parentObject, value, field, ComboBox.class,
				new ComboBoxPanel());
		setup();
		setComponentValue(value);
	}

	public void setup() {
		setupCustomModel();
		setupCustomRenderer();
		setupElements();
	}

	private void setupCustomRenderer() {
		Field field = getField();
		Annotation a = field.getAnnotation(ComboBox.class);
		@SuppressWarnings("unchecked")
		Class<? extends ListCellRenderer> rendererClass = getAnnotationField(
				Class.class, a, "renderer");
		ListCellRenderer renderer = createInstance(rendererClass);
		log.debug("Set new list cell renderer {}.", renderer);
		getComponent().setRenderer(renderer);
	}

	private void setupCustomModel() {
		Field field = getField();
		Annotation a = field.getAnnotation(ComboBox.class);
		@SuppressWarnings("unchecked")
		Class<? extends ComboBoxModel> modelClass = getAnnotationField(
				Class.class, a, "model");
		ComboBoxModel model = createInstance(modelClass);
		log.debug("Set new combo box model {}.", model);
		getComponent().setModel(model);
	}

	private <T> T createInstance(Class<? extends T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	private void setupElements() {
		Collection<?> values = getValuesFromAnnotationIn();
		if (values != null) {
			log.debug("Set new values {}.", values);
			getComponent().setValues(values);
		}
	}

	private Collection<?> getValuesFromAnnotationIn() {
		Object parentObject = getParentObject();
		Field field = getField();
		Annotation a = field.getAnnotation(ComboBox.class);
		String comboBoxName = getAnnotationField(String.class, a, "elements");

		Collection<?> values = (Collection<?>) reflectionToolbox
				.searchObjectWithAnnotationValueIn(parentObject,
						ComboBoxElements.class, comboBoxName, Collection.class);
		return values;
	}

	private <T> T getAnnotationField(Class<? extends T> clazz, Annotation a,
			String name) {
		return reflectionToolbox.invokeMethodWithReturnType(name, clazz, a);
	}

}
