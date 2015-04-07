/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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
package com.anrisoftware.prefdialog.fields.historycombobox;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.inject.Inject;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccess;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.prefdialog.fields.combobox.AbstractComboBoxField;
import com.anrisoftware.prefdialog.fields.combobox.ComboBoxFieldFactory;
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.HistoryComboBoxFactory;
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.HistoryComboBoxModel;
import com.anrisoftware.prefdialog.miscswing.comboboxhistory.ItemDefault;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Field to let the select values in a combo-box. The combo box will retain a
 * history of added items in the model.
 * 
 * @see HistoryComboBox
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class HistoryComboBoxField extends AbstractComboBoxField<JComboBox<?>> {

	private static final Class<? extends Annotation> ANNOTATION_CLASS = HistoryComboBox.class;

	private HistoryComboBoxFieldLogger log;

	private transient AnnotationAccess fieldAnnotation;

	private transient BeanAccessFactory beanAccessFactory;

	private transient HistoryComboBoxFactory boxFactory;

	@SuppressWarnings("rawtypes")
	private Collection history;

	private Collection<?> defaultItems;

	private final ListDataListener historyListener;

	/**
	 * @see ComboBoxFieldFactory#create(Object, String)
	 */
	@AssistedInject
	HistoryComboBoxField(HistoryComboBoxFactory historyComboBoxFactory,
			@Assisted Object parentObject, @Assisted String fieldName) {
		super(ANNOTATION_CLASS, new JComboBox<Object>(), parentObject,
				fieldName);
		this.boxFactory = historyComboBoxFactory;
		this.history = new ArrayList<Object>();
		this.defaultItems = new ArrayList<Object>();
		this.historyListener = new ListDataListener() {

			@Override
			public void intervalRemoved(ListDataEvent e) {
				removeHistory(e.getIndex0(), e.getIndex1());
			}

			@Override
			public void intervalAdded(ListDataEvent e) {
				insertHistory(e.getIndex0(), e.getIndex1());
			}

			@Override
			public void contentsChanged(ListDataEvent e) {
				replaceHistory(e.getIndex0(), e.getIndex1());
			}
		};
	}

	@SuppressWarnings("unchecked")
	private void replaceHistory(int index0, int index1) {
		ComboBoxModel<?> model = getComponent().getModel();
		for (int i = index0; i <= index1; i++) {
			Object element = model.getElementAt(i);
			history.remove(element);
			history.add(element);
		}
	}

	@SuppressWarnings("unchecked")
	private void insertHistory(int index0, int index1) {
		ComboBoxModel<?> model = getComponent().getModel();
		for (int i = index0; i <= index1; i++) {
			Object element = model.getElementAt(i);
			history.add(element);
		}
	}

	private void removeHistory(int index0, int index1) {
		ComboBoxModel<?> model = getComponent().getModel();
		for (int i = index0; i <= index1; i++) {
			Object element = model.getElementAt(i);
			history.remove(element);
		}
	}

	@Inject
	void setupHistoryComboBoxField(HistoryComboBoxFieldLogger logger,
			AnnotationAccessFactory annotationAccessFactory,
			BeanAccessFactory beanAccessFactory) {
		this.log = logger;
		this.fieldAnnotation = annotationAccessFactory.create(ANNOTATION_CLASS,
				getAccessibleObject());
		this.beanAccessFactory = beanAccessFactory;
		Object oldValue = getValue();
		setupDefaultItems();
		setupHistory();
		setupModel();
		setupMaximum();
		getComponent().setSelectedItem(oldValue);
	}

	private void setupDefaultItems() {
		String fieldName = fieldAnnotation.getValue("defaultItems");
		if (isEmpty(fieldName)) {
			return;
		}
		Object parent = getParentObject();
		BeanAccess access = beanAccessFactory.create(fieldName, parent);
		Object value = access.getValue();
		setDefaultItems(value);
	}

	private void setupHistory() {
		String fieldName = fieldAnnotation.getValue("history");
		if (isEmpty(fieldName)) {
			return;
		}
		Object parent = getParentObject();
		BeanAccess access = beanAccessFactory.create(fieldName, parent);
		Collection<?> history = access.getValue();
		setHistory(history);
	}

	private void setupModel() {
		MutableComboBoxModel<?> mutableModel = createMutableModel(getModel());
		boxFactory.create(getComponent(), mutableModel, getRenderer(),
				defaultItems);
		getComponent().getModel().addListDataListener(historyListener);
	}

	private MutableComboBoxModel<?> createMutableModel(ComboBoxModel<?> model) {
		if (model instanceof MutableComboBoxModel) {
			return (MutableComboBoxModel<?>) model;
		}
		DefaultComboBoxModel<Object> mutableModel = new DefaultComboBoxModel<Object>();
		for (int i = 0; i < model.getSize(); i++) {
			mutableModel.addElement(model.getElementAt(i));
		}
		return mutableModel;
	}

	private void setupMaximum() {
		int maximum = fieldAnnotation.getValue("maximumHistory");
		setMaximum(maximum);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setValue(Object value) throws PropertyVetoException {
		if (value instanceof ItemDefault) {
			ItemDefault item = (ItemDefault) value;
			value = item.getItem();
		}
		try {
			super.setValue(value);
		} catch (PropertyVetoException e) {
			((MutableComboBoxModel) getModel()).removeElement(value);
			getComponent().setSelectedItem(value);
			throw e;
		}
	}

	/**
	 * Sets the history collection. The collection will retain the current
	 * history of the field.
	 * 
	 * @param history
	 *            the history {@link Collection}.
	 * 
	 * @throws NullPointerException
	 *             if the specified history collection is {@code null}.
	 */
	public void setHistory(Collection<?> history) {
		log.checkHistory(this, history);
		this.history = history;
		log.historySet(this, history);
	}

	/**
	 * Returns the history collection. The collection will retain the current
	 * history of the field.
	 * 
	 * @return the history {@link Collection}.
	 */
	public Collection<?> getHistory() {
		return history;
	}

	/**
	 * Sets the specified default items for this combo box field.
	 * 
	 * @param items
	 *            the items to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified items are {@code null}.
	 * 
	 * @throws IllegalArgumentException
	 *             if the items type is not of array or {@link Collection}.
	 */
	private void setDefaultItems(Object items) {
		log.checkDefaultItems(this, items);
		if (items.getClass().isArray()) {
			setDefaultItems((Object[]) items);
		} else if (items instanceof Collection) {
			setDefaultItems((Collection<?>) items);
		} else {
			throw log.unsupportedType(this, items);
		}
	}

	/**
	 * Sets the specified default items for this combo box field.
	 * 
	 * @param items
	 *            the default items array to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified items are {@code null}.
	 */
	private void setDefaultItems(Object[] elements) {
		log.checkDefaultItems(this, elements);
		this.defaultItems = Arrays.asList(elements);
		log.defaultItemsSet(this, elements);
	}

	/**
	 * Sets the specified default items for this combo box field.
	 * 
	 * @param items
	 *            the {@link Collection} items to set.
	 * 
	 * @throws NullPointerException
	 *             if the specified items are {@code null}.
	 */
	private void setDefaultItems(Collection<?> items) {
		log.checkDefaultItems(this, items);
		this.defaultItems = items;
		log.defaultItemsSet(this, items);
	}

	/**
	 * Sets the maximum entries in the history, excluding the default items. If
	 * the box contains more then the maximum entries then the last entry is
	 * removed.
	 * 
	 * @param maximum
	 *            the maximum entries.
	 * 
	 * @throws IllegalArgumentException
	 *             if the maximum is negative.
	 */
	public void setMaximum(int maximum) {
		log.checkMaximum(this, maximum);
		((HistoryComboBoxModel) getModel()).setMaximum(maximum);
		log.maximumSet(this, maximum);
	}

	/**
	 * Returns the maximum entries in the history, excluding the default items.
	 * 
	 * @return the maximum entries.
	 */
	public int getMaximum() {
		return ((HistoryComboBoxModel) getModel()).getMaximum();
	}
}
