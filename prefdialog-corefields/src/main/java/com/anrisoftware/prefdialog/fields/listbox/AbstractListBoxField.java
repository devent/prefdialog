/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.fields.listbox;

import static com.anrisoftware.prefdialog.miscswing.lockedevents.LockedListSelectionListener.lockedListSelectionListener;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClass;
import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccess;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.core.AbstractTitleScrollField;
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.lockedevents.LockedListSelectionListener;

/**
 * Implements the list box field.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public abstract class AbstractListBoxField<ComponentType extends JList<?>>
		extends AbstractTitleScrollField<ComponentType> {

	private static final String RENDERER_ELEMENT = "renderer";

	private static final String MODEL_ELEMENT = "model";

	private static final String ELEMENTS_ELEMENT = "elements";

	private AbstractListBoxFieldLogger log;

	private final Class<? extends Annotation> annotationType;

	private transient AnnotationAccess fieldAnnotation;

	private transient AnnotationClass<?> annotationClass;

	private transient BeanAccessFactory beanAccessFactory;

	private final LockedListSelectionListener dataListener;

	/**
	 * @see AbstractTitleField#AbstractTitleField(java.awt.Component, Object,
	 *      String)
	 * 
	 * @param annotationType
	 *            the annotation {@link Class} type of the specific combo box
	 *            field. The annotation must have the following attributes:
	 *            <ul>
	 *            <li>model</li>
	 *            <li>modelClass</li>
	 *            <li>renderer</li>
	 *            <li>rendererClass</li>
	 *            <li>elements</li>
	 *            </ul>
	 */
	protected AbstractListBoxField(Class<? extends Annotation> annotationType,
			ComponentType component, Object parentObject, String fieldName) {
		super(component, parentObject, fieldName);
		this.annotationType = annotationType;
		this.dataListener = lockedListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					setSelectedIndices(getComponent().getSelectedIndices());
				} catch (PropertyVetoException e1) {
				}
			}
		});
	}

	/**
	 * Reads the attributes of the annotation and setups the field.
	 */
	@Inject
	void setupField(AbstractListBoxFieldLogger logger,
			AnnotationAccessFactory annotationAccessFactory,
			AnnotationClassFactory classFactory,
			BeanAccessFactory beanAccessFactory) {
		this.log = logger;
		this.annotationClass = classFactory.create(getParentObject(),
				annotationType, getAccessibleObject());
		this.fieldAnnotation = annotationAccessFactory.create(annotationType,
				getAccessibleObject());
		this.beanAccessFactory = beanAccessFactory;
		setupModel();
		setupRenderer();
		setupElements();
		setupListBox();
	}

	private void setupListBox() {
		ComponentType component = getComponent();
		setupListValues();
		component.getSelectionModel().addListSelectionListener(dataListener);
	}

	private void setupListValues() {
		try {
			Object value = getValue();
			setValues(value);
		} catch (PropertyVetoException e) {
			log.vetoedValues(this, e);
		}
	}

	private void setupModel() {
		ListModel<?> model = (ListModel<?>) annotationClass.forAttribute(
				MODEL_ELEMENT).build();
		if (model != null) {
			setModel(model);
		}
	}

	private void setupRenderer() {
		ListCellRenderer<?> renderer = (ListCellRenderer<?>) annotationClass
				.forAttribute(RENDERER_ELEMENT).build();
		if (renderer != null) {
			setRenderer(renderer);
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
	public void setValue(Object value) throws PropertyVetoException {
		try {
			super.setValue(value);
			getComponent().setSelectedValue(value, true);
		} catch (PropertyVetoException e) {
			throw e;
		}
	}

	@SuppressWarnings("rawtypes")
	private void setValues(Object values) throws PropertyVetoException {
		if (values.getClass().isArray()) {
			setValues((Object[]) values);
		} else if (values instanceof Collection) {
			setValues((Collection) values);
		} else {
			setValue(values);
		}
	}

	/**
	 * Sets the values as the selected values of the list.
	 * 
	 * @param values
	 *            the {@link Collection} values.
	 * 
	 * @throws PropertyVetoException
	 */
	@SuppressWarnings("rawtypes")
	public void setValues(Collection values) throws PropertyVetoException {
		Object value = getValue();
		if (value.getClass().isArray()) {
			setValue(values.toArray());
		} else if (value instanceof Collection) {
			setValue(values);
		}
	}

	/**
	 * Sets the values as the selected values of the list.
	 * 
	 * @param values
	 *            the values array.
	 * 
	 * @throws PropertyVetoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setValues(Object[] values) throws PropertyVetoException {
		Object value = getValue();
		if (value.getClass().isArray()) {
			setValue(values);
		} else if (value instanceof Collection) {
			Collection collection = (Collection) value;
			collection.clear();
			collection.addAll(asList(values));
			setValue(collection);
		}
	}

	/**
	 * Sets the selected indices.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @param indices
	 *            the selected indices.
	 * 
	 * @throws PropertyVetoException
	 *             if the first index's value is vetoed.
	 * 
	 * @see JList#setSelectedIndices(int[])
	 */
	@OnAwt
	public void setSelectedIndices(int[] indices) throws PropertyVetoException {
		ComponentType list = getComponent();
		ListModel<?> model = list.getModel();
		if (indices.length == 1) {
			setValue(model.getElementAt(indices[0]));
		} else if (indices.length > 1) {
			setValues(findSelectedValues(indices));
		}
		dataListener.lock();
		list.setSelectedIndices(indices);
		dataListener.unlock();
	}

	private Object[] findSelectedValues(int[] indices) {
		ComponentType list = getComponent();
		ListModel<?> model = list.getModel();
		Object[] values = new Object[indices.length];
		for (int i : indices) {
			values[i] = model.getElementAt(indices[i]);
		}
		return values;
	}

	/**
	 * Returns the selected indices.
	 * <p>
	 * <h2>AWT Thread</h2>
	 * <p>
	 * Should be called in the AWT thread.
	 * 
	 * @return the selected indices.
	 * 
	 * @see JList#getSelectedIndices()
	 */
	@OnAwt
	public int[] getSelectedIndices() {
		return getComponent().getSelectedIndices();
	}

	/**
	 * Sets the specified model for the list box.
	 * 
	 * @param model
	 *            the {@link ListModel}.
	 * 
	 * @throws NullPointerException
	 *             if the specified model is {@code null}.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setModel(ListModel<?> model) {
		log.checkModel(this, model);
		getComponent().setModel((ListModel) model);
		log.modelSet(this, model);
	}

	/**
	 * Returns the model of the list box.
	 * 
	 * @return the {@link ListModel}.
	 */
	public ListModel<?> getModel() {
		return getComponent().getModel();
	}

	/**
	 * Sets the specified renderer for the combo box field.
	 * 
	 * @param renderer
	 *            the {@link ListCellRenderer}.
	 * 
	 * @throws NullPointerException
	 *             if the specified renderer is {@code null}.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setRenderer(ListCellRenderer<?> renderer) {
		log.checkRenderer(this, renderer);
		getComponent().setCellRenderer((ListCellRenderer) renderer);
		log.rendererSet(this, renderer);
	}

	/**
	 * Returns the renderer of the combo box field.
	 * 
	 * @return the {@link ListCellRenderer}.
	 */
	public ListCellRenderer<?> getRenderer() {
		return getComponent().getCellRenderer();
	}

	private void setElements(Object elements) {
		log.checkElements(this, elements);
		if (elements.getClass().isArray()) {
			setElements((Object[]) elements);
		} else if (elements instanceof Iterable) {
			setElements((Iterable<?>) elements);
		} else {
			log.unsupportedType(this, elements);
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
		DefaultListModel model = new DefaultListModel();
		for (Object e : elements) {
			model.addElement(e);
		}
		getComponent().setModel(model);
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
		DefaultListModel model = new DefaultListModel();
		for (Object e : elements) {
			model.addElement(e);
		}
		getComponent().setModel(model);
		log.elementsSet(this, elements);
	}

}
