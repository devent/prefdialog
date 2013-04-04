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

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.awt.Container;
import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Vector;

import javax.inject.Inject;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccess;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.prefdialog.annotations.ComboBox;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Combo box field. A combo box field offers multiple items in a drop-down list.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class ComboBoxField extends AbstractTitleField<JComboBox<?>, Container> {

	private static final String EDITABLE_ELEMENT = "editable";

	private static final String RENDERER_CLASS_ELEMENT = "rendererClass";

	private static final String MODEL_CLASS_ELEMENT = "modelClass";

	private static final String RENDERER_ELEMENT = "renderer";

	private static final String MODEL_ELEMENT = "model";

	private static final String ELEMENTS_ELEMENT = "elements";

	private static final Class<? extends Annotation> ANNOTATION_CLASS = ComboBox.class;

	private final ComboBoxFieldLogger log;

	private final JComboBox<?> comboBox;

	private boolean adjusting;

	private AnnotationAccess fieldAnnotation;

	private BeanAccessFactory beanAccessFactory;

	/**
	 * @see ComboBoxFieldFactory#create(Container, Object, String)
	 */
	@AssistedInject
	ComboBoxField(ComboBoxFieldLogger logger, @Assisted Container container,
			@Assisted Object parentObject, @Assisted String fieldName) {
		this(logger, new JComboBox<Object>(), container, parentObject,
				fieldName);
	}

	/**
	 * @see ComboBoxFieldFactory#create(JComboBox, Container, Object, String)
	 */
	@AssistedInject
	ComboBoxField(ComboBoxFieldLogger logger, @Assisted JComboBox<?> comboBox,
			@Assisted Container container, @Assisted Object parentObject,
			@Assisted String fieldName) {
		super(comboBox, container, parentObject, fieldName);
		this.log = logger;
		this.comboBox = comboBox;
		this.adjusting = false;
	}

	@Inject
	void setBeanAccessFactory(AnnotationAccessFactory annotationAccessFactory,
			BeanAccessFactory beanAccessFactory) {
		this.fieldAnnotation = annotationAccessFactory.create(ANNOTATION_CLASS,
				getAccessibleObject());
		this.beanAccessFactory = beanAccessFactory;
		setupModel();
		setupModelClass();
		setupRenderer();
		setupRendererClass();
		setupElements();
		setupEditable();
	}

	private void setupEditable() {
		boolean editable = fieldAnnotation.getValue(EDITABLE_ELEMENT);
		setEditable(editable);
	}

	private void setupRendererClass() {
		Class<? extends ListCellRenderer<?>> type = fieldAnnotation
				.getValue(RENDERER_CLASS_ELEMENT);
		if (type != DefaultListCellRenderer.class) {
			setRendererFromClass(type);
		}
	}

	private void setRendererFromClass(Class<? extends ListCellRenderer<?>> type) {
		ListCellRenderer<?> renderer = getBeanFactory().create(type);
		setRenderer(renderer);
	}

	private void setupModelClass() {
		Class<? extends ComboBoxModel<?>> type = fieldAnnotation
				.getValue(MODEL_CLASS_ELEMENT);
		if (type != DefaultComboBoxModel.class) {
			setModelFromClass(type);
		}
	}

	private void setModelFromClass(Class<? extends ComboBoxModel<?>> type) {
		ComboBoxModel<?> model = getBeanFactory().create(type);
		setModel(model);
	}

	private void setupModel() {
		String fieldName = fieldAnnotation.getValue(MODEL_ELEMENT);
		if (!isEmpty(fieldName)) {
			setModelFromField(fieldName);
		}
	}

	private void setModelFromField(String fieldName) {
		Object parent = getParentObject();
		BeanAccess access = beanAccessFactory.create(fieldName, parent);
		ComboBoxModel<?> value = access.getValue();
		value = value == null ? createModelFromField(access) : value;
		setModel(value);
	}

	@SuppressWarnings("unchecked")
	private ComboBoxModel<?> createModelFromField(BeanAccess access) {
		Class<? extends ComboBoxModel<?>> type;
		type = (Class<? extends ComboBoxModel<?>>) access.getType();
		ComboBoxModel<?> model = getBeanFactory().create(type);
		try {
			access.setValue(model);
			return model;
		} catch (PropertyVetoException e) {
			throw log.errorSetModel(this, e);
		}
	}

	private void setupRenderer() {
		String fieldName = fieldAnnotation.getValue(RENDERER_ELEMENT);
		if (!isEmpty(fieldName)) {
			setRendererFromField(fieldName);
		}
	}

	private void setRendererFromField(String fieldName) {
		Object parent = getParentObject();
		BeanAccess access = beanAccessFactory.create(fieldName, parent);
		ListCellRenderer<?> value = access.getValue();
		value = value == null ? createRendererFromField(access) : value;
		setRenderer(value);
	}

	@SuppressWarnings("unchecked")
	private ListCellRenderer<?> createRendererFromField(BeanAccess access) {
		Class<? extends ListCellRenderer<?>> type;
		type = (Class<? extends ListCellRenderer<?>>) access.getType();
		ListCellRenderer<?> renderer = getBeanFactory().create(type);
		try {
			access.setValue(renderer);
			return renderer;
		} catch (PropertyVetoException e) {
			throw log.errorSetRenderer(this, e);
		}
	}

	private void setupElements() {
		String fieldName = fieldAnnotation.getValue(ELEMENTS_ELEMENT);
		if (isEmpty(fieldName)) {
			return;
		}
		Object parent = getParentObject();
		BeanAccess access = beanAccessFactory.create(fieldName, parent);
		Object value = access.getValue();
		setElements(value);
	}

	@Override
	public void applyInput() throws PropertyVetoException {
		super.applyInput();
		Object value = comboBox.getModel().getSelectedItem();
		adjusting = true;
		setValue(value);
		adjusting = false;
	}

	@Override
	public void restoreInput() {
		super.restoreInput();
		comboBox.setSelectedItem(getValue());
	}

	@Override
	public void setValue(Object value) throws PropertyVetoException {
		super.setValue(value);
		if (!adjusting) {
			comboBox.setSelectedItem(value);
		}
	}

	/**
	 * Sets the specified model for the combo box field.
	 * 
	 * @param model
	 *            the {@link ComboBoxModel}.
	 * 
	 * @throws NullPointerException
	 *             if the specified model is {@code null}.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setModel(ComboBoxModel<?> model) {
		log.checkModel(this, model);
		comboBox.setModel((ComboBoxModel) model);
		log.modelSet(this, model);
	}

	/**
	 * Returns the model of the combo box field.
	 * 
	 * @return the {@link ComboBoxModel}.
	 */
	public ComboBoxModel<?> getModel() {
		return comboBox.getModel();
	}

	/**
	 * Sets the specified renderer for the combo box field.
	 * 
	 * @param model
	 *            the {@link ListCellRenderer}.
	 * 
	 * @throws NullPointerException
	 *             if the specified renderer is {@code null}.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setRenderer(ListCellRenderer<?> renderer) {
		log.checkRenderer(this, renderer);
		comboBox.setRenderer((ListCellRenderer) renderer);
		log.rendererSet(this, renderer);
	}

	/**
	 * Returns the renderer of the combo box field.
	 * 
	 * @return the {@link ListCellRenderer}.
	 */
	public ListCellRenderer<?> getRenderer() {
		return comboBox.getRenderer();
	}

	/**
	 * Sets the specified elements for this combo box fields.
	 * 
	 * @param elements
	 *            the elements to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified elements are {@code null}.
	 * 
	 * @throws IllegalArgumentException
	 *             if the elements type is not of array or {@link Iterable}.
	 */
	public void setElements(Object elements) {
		log.checkElements(this, elements);
		if (elements.getClass().isArray()) {
			setElements((Object[]) elements);
		} else if (elements instanceof Iterable) {
			setElements(elements);
		} else {
			throw log.unsupportedType(this, elements);
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
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setElements(Object[] elements) {
		log.checkElements(this, elements);
		comboBox.setModel(new DefaultComboBoxModel(elements));
		log.elementsSet(this, elements);
	}

	/**
	 * Sets the specified elements for this combo box fields.
	 * 
	 * @param elements
	 *            the {@link List} elements to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified elements are {@code null}.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setElements(Iterable<?> elements) {
		log.checkElements(this, elements);
		comboBox.setModel(new DefaultComboBoxModel(asList(elements)));
		log.elementsSet(this, elements);
	}

	private Vector<Object> asList(Iterable<?> elements) {
		Vector<Object> list = new Vector<Object>();
		for (Object object : elements) {
			list.add(object);
		}
		return list;
	}

	/**
	 * Sets if the field should be editable.
	 * 
	 * @param editable
	 *            {@code true} if the combo box should be editable or
	 *            {@code false} if not.
	 */
	public void setEditable(boolean editable) {
		comboBox.setEditable(editable);
	}

	/**
	 * Returns if the field should is editable.
	 * 
	 * @return {@code true} if the combo box is editable or {@code false} if
	 *         not.
	 */
	public boolean isEditable() {
		return comboBox.isEditable();
	}

}
