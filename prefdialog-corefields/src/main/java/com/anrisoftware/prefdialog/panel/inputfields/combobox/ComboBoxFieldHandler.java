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
package com.anrisoftware.prefdialog.panel.inputfields.combobox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

import javax.annotation.Nullable;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ListCellRenderer;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.annotations.ComboBoxElements;
import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the {@link ComboBoxPanel} as the managed component.
 * 
 * The additional attributes are:
 * <ul>
 * <li>renderer</li>
 * <li>model</li>
 * <li>elements</li>
 * </ul>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ComboBoxFieldHandler extends AbstractLabelFieldHandler<ComboBoxPanel> {

	/**
	 * Sets the parameter of the {@link ComboBoxPanel}.
	 * 
	 * @param panel
	 *            the {@link ComboBoxPanel}.
	 * 
	 * @param parentObject
	 *            the {@link Object} where the field is defined.
	 * 
	 * @param value
	 *            the value of the field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 */
	@Inject
	ComboBoxFieldHandler(ComboBoxPanel panel,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") @Nullable Object value, @Assisted Field field) {
		super(parentObject, value, field, ComboBox.class, panel);
	}

	/**
	 * Set a custom model, a custom renderer and the elements for the combobox.
	 */
	@Override
	public FieldHandler<ComboBoxPanel> setup() {
		setupCustomModelClass();
		setupCustomRendererClass();
		setupElements();
		return super.setup();
	}

	private void setupCustomRendererClass() {
		Field field = getField();
		Annotation a = field.getAnnotation(ComboBox.class);
		Class<? extends ListCellRenderer> rendererClass = getRenderer(a);
		if (rendererClass == DefaultListCellRenderer.class) {
			return;
		}
		ListCellRenderer renderer = createInstance(rendererClass);
		getComponent().setRenderer(renderer);
	}

	@SuppressWarnings("unchecked")
	private Class<? extends ListCellRenderer> getRenderer(Annotation a) {
		return getReflectionToolbox().invokeMethodWithReturnType(
				"rendererClass", Class.class, a);
	}

	private void setupCustomModelClass() {
		Field field = getField();
		Annotation a = field.getAnnotation(ComboBox.class);
		Class<? extends ComboBoxModel> modelClass = getModel(a);
		ComboBoxModel model = createInstance(modelClass);
		getComponent().setModel(model);
	}

	@SuppressWarnings("unchecked")
	private Class<? extends ComboBoxModel> getModel(Annotation a) {
		return getReflectionToolbox().invokeMethodWithReturnType("modelClass",
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
		return ((Collection<?>) getReflectionToolbox()
				.searchObjectWithAnnotationValueIn(parentObject,
						ComboBoxElements.class, elements, Collection.class));
	}

	private String getElements(Annotation a) {
		return getReflectionToolbox().invokeMethodWithReturnType("elements",
				String.class, a);
	}
}
