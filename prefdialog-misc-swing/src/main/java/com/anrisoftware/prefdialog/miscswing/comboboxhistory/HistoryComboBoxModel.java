package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import static com.google.inject.Guice.createInjector;
import static java.util.Collections.synchronizedSet;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;

import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

@SuppressWarnings("serial")
public class HistoryComboBoxModel<E> implements MutableComboBoxModel<E>,
		Serializable {

	private static HistoryComboBoxModelFactory factory;

	/**
	 * @see HistoryComboBoxModelFactory#create(MutableComboBoxModel, Set)
	 */
	@SuppressWarnings("unchecked")
	public static <E> HistoryComboBoxModel<E> decorate(
			MutableComboBoxModel<E> model, Set<E> defaultItems) {
		return (HistoryComboBoxModel<E>) getFactory().create(model,
				defaultItems);
	}

	private static HistoryComboBoxModelFactory getFactory() {
		if (factory == null) {
			synchronized (HistoryComboBoxModel.class) {
				Injector injector = createInjector(new ComboBoxHistoryModule());
				factory = injector
						.getInstance(HistoryComboBoxModelFactory.class);
			}
		}
		return factory;
	}

	private final MutableComboBoxModel<E> model;

	private final Set<E> defaultItems;

	private final Set<E> items;

	private int maximum;

	/**
	 * @see HistoryComboBoxModelFactory#create(MutableComboBoxModel, Set)
	 */
	@Inject
	HistoryComboBoxModel(@Assisted MutableComboBoxModel<E> model,
			@Assisted Set<E> defaultItems) {
		this.maximum = 5;
		this.model = model;
		this.defaultItems = defaultItems;
		this.items = synchronizedSet(new HashSet<E>());
		insertDefaultItems();
	}

	private void insertDefaultItems() {
		items.addAll(defaultItems);
		for (E item : defaultItems) {
			model.addElement(item);
		}
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
		while (getCustomItemsSize() >= maximum) {
			removeElementAt(getLastItemIndex());
		}
	}

	public int getMaximum() {
		return maximum;
	}

	@Override
	public void addElement(E item) {
		if (items.contains(item)) {
			setSelectedItem(item);
		} else {
			removeCustomElementFromEnd();
			model.insertElementAt(item, 0);
			items.add(item);
		}
	}

	private void removeCustomElementFromEnd() {
		if (getCustomItemsSize() == maximum) {
			E item = getElementAt(getLastItemIndex());
			items.remove(item);
			model.removeElement(item);
		}
	}

	@Override
	public void removeElement(Object obj) {
		if (!defaultItems.contains(obj)) {
			model.removeElement(obj);
			items.remove(obj);
		}
	}

	private int getCustomItemsSize() {
		return getSize() - defaultItems.size();
	}

	private int getLastItemIndex() {
		int i = getSize() - 1;
		for (; i >= 0; i--) {
			if (!defaultItems.contains(getElementAt(i))) {
				break;
			}
		}
		return i;
	}

	@Override
	public int getSize() {
		return model.getSize();
	}

	@Override
	public void setSelectedItem(Object anItem) {
		model.setSelectedItem(anItem);
	}

	@Override
	public E getElementAt(int index) {
		return model.getElementAt(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		model.addListDataListener(l);
	}

	@Override
	public void insertElementAt(E item, int index) {
		if (items.contains(getElementAt(index))) {
			model.setSelectedItem(item);
		} else {
			model.insertElementAt(item, index);
			items.add(item);
		}
	}

	@Override
	public Object getSelectedItem() {
		return model.getSelectedItem();
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		model.removeListDataListener(l);
	}

	@Override
	public void removeElementAt(int index) {
		E item = getElementAt(index);
		if (!items.contains(item)) {
			model.removeElement(item);
			items.remove(item);
		}
	}

}
