/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.fields.combobox;

import java.awt.Container;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import javax.inject.Inject;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;

import org.apache.commons.lang3.StringUtils;

import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.google.inject.assistedinject.Assisted;

/**
 * Combo box field. A combo box field offers multiple items in a drop-down list.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
@SuppressWarnings("rawtypes")
public class ComboBoxField extends AbstractTitleField<JComboBox, Container> {

	private static final String MODEL_ELEMENT = "model";

	private static final String ELEMENTS_ELEMENT = "elements";

	private static final Class<? extends Annotation> ANNOTATION_CLASS = ComboBox.class;

	private final ComboBoxFieldLogger log;

	@Inject
	ComboBoxField(ComboBoxFieldLogger logger, @Assisted Container container,
			@Assisted Object parentObject, @Assisted Field field) {
		super(new JComboBox(), container, parentObject, field);
		this.log = logger;
		setup();
	}

	private void setup() {
	}

	@Override
	protected void afterName() {
		setupModel();
		setupElements();
		super.afterName();
	}

	private void setupModel() {
		String modelFieldName = getAnnotationAccess().getValue(
				ANNOTATION_CLASS, getField(), MODEL_ELEMENT);
		if (StringUtils.isEmpty(modelFieldName)) {
			return;
		}
		Object parent = getParentObject();
		ComboBoxModel value = getBeanAccess().getValue(modelFieldName, parent);
		setModel(value);
	}

	private void setupElements() {
		String elementsFieldName = getAnnotationAccess().getValue(
				ANNOTATION_CLASS, getField(), ELEMENTS_ELEMENT);
		Object parent = getParentObject();
		Object value = getBeanAccess().getValue(elementsFieldName, parent);
		setElements(value);
	}

	@Override
	public void setValue(Object newValue) {
		super.setValue(newValue);
		getComponent().setSelectedItem(newValue);
	}

	/**
	 * Sets the specified model for the combo box field.
	 * 
	 * @param model
	 *            the {@link ComboBoxModel}.
	 */
	@SuppressWarnings("unchecked")
	public void setModel(ComboBoxModel model) {
		log.checkModel(this, model);
		getComponent().setModel(model);
		log.modelSet(this, model);
	}

	/**
	 * Returns the model of the combo box field.
	 * 
	 * @return the {@link ComboBoxModel}.
	 */
	public ComboBoxModel getModel() {
		return getComponent().getModel();
	}

	/**
	 * Sets the specified elements for this combo box fields.
	 * 
	 * @param newElements
	 *            the elements to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified elements are {@code null}.
	 * 
	 * @throws IllegalArgumentException
	 *             if the elements type is not of array or {@link List}.
	 * 
	 * @throws ClassCastException
	 *             if the current model is not of type
	 *             {@link MutableComboBoxModel}.
	 */
	public void setElements(Object newElements) {
		log.checkElements(this, newElements);
		if (newElements.getClass().isArray()) {
			setElements((Object[]) newElements);
		} else if (newElements instanceof List) {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) newElements;
			setElements(list);
		} else {
			throw log.unsupportedType(this, newElements);
		}
	}

	/**
	 * Sets the specified elements for this combo box fields.
	 * 
	 * @param newElements
	 *            the elements array to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified elements are {@code null}.
	 * 
	 * @throws ClassCastException
	 *             if the current model is not of type
	 *             {@link MutableComboBoxModel}.
	 */
	@SuppressWarnings("unchecked")
	public void setElements(Object[] newElements) {
		log.checkElements(this, newElements);
		MutableComboBoxModel model = (MutableComboBoxModel) getModel();
		for (Object item : newElements) {
			model.addElement(item);
		}
		log.elementsSet(this, newElements);
	}

	/**
	 * Sets the specified elements for this combo box fields.
	 * 
	 * @param newElements
	 *            the {@link List} elements to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified elements are {@code null}.
	 * 
	 * @throws ClassCastException
	 *             if the current model is not of type
	 *             {@link MutableComboBoxModel}.
	 */
	@SuppressWarnings("unchecked")
	public void setElements(List<Object> newElements) {
		log.checkElements(this, newElements);
		MutableComboBoxModel model = (MutableComboBoxModel) getModel();
		for (Object item : newElements) {
			model.addElement(item);
		}
		log.elementsSet(this, newElements);
	}
}
