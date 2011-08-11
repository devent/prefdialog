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
package com.globalscalingsoftware.prefdialog.panel.inputfield.combobox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

import javax.annotation.Nullable;
import javax.swing.ComboBoxModel;
import javax.swing.ListCellRenderer;

import com.globalscalingsoftware.prefdialog.annotations.ComboBox;
import com.globalscalingsoftware.prefdialog.annotations.ComboBoxElements;
import com.globalscalingsoftware.prefdialog.panel.inputfield.AbstractLabelFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfield.combobox.LoggerFactory.Logger;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class ComboBoxFieldHandler extends AbstractLabelFieldHandler<ComboBoxPanel> {

	private final Logger log;

	@Inject
	ComboBoxFieldHandler(LoggerFactory loggerFactory,
			ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") @Nullable Object value, @Assisted Field field) {
		super(reflectionToolbox, parentObject, value, field, ComboBox.class,
				new ComboBoxPanel());
		this.log = loggerFactory.create(ComboBoxFieldHandler.class);
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
		Class<? extends ListCellRenderer> rendererClass = getRenderer(a);
		ListCellRenderer renderer = createInstance(rendererClass);
		log.setRenderer(getField(), renderer);
		getComponent().setRenderer(renderer);
	}

	@SuppressWarnings("unchecked")
	private Class<? extends ListCellRenderer> getRenderer(Annotation a) {
		return reflectionToolbox.invokeMethodWithReturnType("renderer",
				Class.class, a);
	}

	private void setupCustomModel() {
		Field field = getField();
		Annotation a = field.getAnnotation(ComboBox.class);
		Class<? extends ComboBoxModel> modelClass = getModel(a);
		ComboBoxModel model = createInstance(modelClass);
		log.setModel(getField(), model);
		getComponent().setModel(model);
	}

	@SuppressWarnings("unchecked")
	private Class<? extends ComboBoxModel> getModel(Annotation a) {
		return reflectionToolbox.invokeMethodWithReturnType("model",
				Class.class, a);
	}

	private <T> T createInstance(Class<? extends T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private void setupElements() {
		Collection<?> values = getValuesFromAnnotationIn();
		if (values != null) {
			log.setValues(getField(), values);
			getComponent().setValues(values);
		}
	}

	private Collection<?> getValuesFromAnnotationIn() {
		Object parentObject = getParentObject();
		Field field = getField();
		Annotation a = field.getAnnotation(ComboBox.class);
		String elements = getElements(a);
		return getElementsCollection(parentObject, elements);
	}

	private Collection<?> getElementsCollection(Object parentObject,
			String elements) {
		return ((Collection<?>) reflectionToolbox
				.searchObjectWithAnnotationValueIn(parentObject,
						ComboBoxElements.class, elements, Collection.class));
	}

	private String getElements(Annotation a) {
		return reflectionToolbox.invokeMethodWithReturnType("elements",
				String.class, a);
	}

}
