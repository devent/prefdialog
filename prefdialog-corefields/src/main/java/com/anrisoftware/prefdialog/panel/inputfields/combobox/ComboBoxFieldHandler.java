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

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.lang.reflect.Field;
import java.util.Collection;

import javax.annotation.Nullable;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ListCellRenderer;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.annotations.ComboBox;
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
		setupCustomModel();
		setupCustomModelClass();
		setupCustomRendererClass();
		setupElements();
		return super.setup();
	}

	private void setupCustomModel() {
		ComboBoxModel model = getModel();
		if (model != null) {
			getComponent().setModel(model);
		}
	}

	private ComboBoxModel getModel() {
		String fieldName = getModelFromA();
		if (isEmpty(fieldName)) {
			return null;
		}
		return getReflectionToolbox().valueFromField(getParentObject(),
				fieldName, ComboBoxModel.class);
	}

	private String getModelFromA() {
		return getReflectionToolbox().valueFromA(getField(), "model",
				String.class, getAnnotationClass());
	}

	private void setupCustomRendererClass() {
		Class<? extends ListCellRenderer> rendererClass = getRendererClass();
		if (rendererClass != DefaultListCellRenderer.class) {
			ListCellRenderer renderer = createInstance(rendererClass);
			getComponent().setRenderer(renderer);
		}
	}

	@SuppressWarnings("unchecked")
	private Class<? extends ListCellRenderer> getRendererClass() {
		return getReflectionToolbox().valueFromA(getField(), "rendererClass",
				Class.class, getAnnotationClass());
	}

	private void setupCustomModelClass() {
		Class<? extends ComboBoxModel> modelClass = getModelClass();
		if (modelClass != DefaultComboBoxModel.class) {
			ComboBoxModel model = createInstance(modelClass);
			getComponent().setModel(model);
		}
	}

	@SuppressWarnings("unchecked")
	private Class<? extends ComboBoxModel> getModelClass() {
		return getReflectionToolbox().valueFromA(getField(), "modelClass",
				Class.class, getAnnotationClass());
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
		Collection<?> elements = getElements();
		if (elements != null) {
			getComponent().setValues(elements);
		}
	}

	private Collection<?> getElements() {
		String fieldName = getElementsFromA();
		if (isEmpty(fieldName)) {
			return null;
		}
		return getReflectionToolbox().valueFromField(getParentObject(),
				fieldName, Collection.class);
	}

	private String getElementsFromA() {
		return getReflectionToolbox().valueFromA(getField(), "elements",
				String.class, getAnnotationClass());
	}
}
