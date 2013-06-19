package com.anrisoftware.prefdialog.fields.historycombobox;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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
public class HistoryComboBoxField extends AbstractComboBoxField {

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
		super(parentObject, fieldName);
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
	protected void replaceHistory(int index0, int index1) {
		ComboBoxModel<?> model = getComponent().getModel();
		for (int i = index0; i <= index1; i++) {
			Object element = model.getElementAt(i);
			history.remove(element);
			history.add(element);
		}
	}

	@SuppressWarnings("unchecked")
	protected void insertHistory(int index0, int index1) {
		ComboBoxModel<?> model = getComponent().getModel();
		for (int i = index0; i <= index1; i++) {
			Object element = model.getElementAt(i);
			history.add(element);
		}
	}

	protected void removeHistory(int index0, int index1) {
		ComboBoxModel<?> model = getComponent().getModel();
		for (int i = index0; i <= index1; i++) {
			Object element = model.getElementAt(i);
			history.remove(element);
		}
	}

	@Override
	protected Class<? extends Annotation> getAnnotationClass() {
		return ANNOTATION_CLASS;
	}

	@Override
	protected void changeVetoableValue(PropertyChangeEvent evt)
			throws PropertyVetoException {
		Object newValue = evt.getNewValue();
		if (newValue instanceof ItemDefault) {
			ItemDefault item = (ItemDefault) newValue;
			newValue = item.getItem();
		}
		super.changeVetoableValue(new PropertyChangeEvent(evt.getSource(), evt
				.getPropertyName(), evt.getOldValue(), newValue));
	}

	@Inject
	void setupHistoryComboBoxField(HistoryComboBoxFieldLogger logger,
			AnnotationAccessFactory annotationAccessFactory,
			BeanAccessFactory beanAccessFactory) {
		this.log = logger;
		this.fieldAnnotation = annotationAccessFactory.create(
				getAnnotationClass(), getAccessibleObject());
		this.beanAccessFactory = beanAccessFactory;
		setupDefaultItems();
		setupHistory();
		setupModel();
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
		Set<Object> defaultsSet = new HashSet<Object>(defaultItems);
		MutableComboBoxModel<?> mutableModel = createMutableModel(getModel());
		boxFactory.create(getComponent(), mutableModel, defaultsSet);
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
	 *             if the items type is not of array or {@link Iterable}.
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
}
