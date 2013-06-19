package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import static java.util.Collections.synchronizedSet;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataListener;

import com.google.inject.assistedinject.Assisted;

/**
 * Retains a history of added items in the model. In addition a set of default
 * items can be set that can not be removed from the model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class HistoryComboBoxModel implements MutableComboBoxModel, Serializable {

	/**
	 * @see HistoryComboBoxModelFactory#create(MutableComboBoxModel, Set)
	 */
	public static HistoryComboBoxModel decorate(MutableComboBoxModel model,
			Set defaultItems) {
		return ComboBoxHistoryModule.getInjector()
				.getInstance(HistoryComboBoxModelFactory.class)
				.create(model, defaultItems);
	}

	private final MutableComboBoxModel model;

	private final Set<Object> defaultItems;

	private final Set<Object> items;

	private final ItemDefaultFactory itemDefaultFactory;

	private int maximum;

	/**
	 * @see HistoryComboBoxModelFactory#create(MutableComboBoxModel, Set)
	 */
	@Inject
	HistoryComboBoxModel(ItemDefaultFactory itemDefaultFactory,
			@Assisted MutableComboBoxModel model, @Assisted Set defaultItems) {
		this.maximum = 5;
		this.model = model;
		this.itemDefaultFactory = itemDefaultFactory;
		this.defaultItems = createDefaultItems(defaultItems);
		this.items = synchronizedSet(new HashSet());
		insertDefaultItems();
	}

	private Set<Object> createDefaultItems(Set items) {
		Set<Object> set = new HashSet<Object>();
		for (Object item : items) {
			set.add(itemDefaultFactory.create(item));
		}
		return set;
	}

	private void insertDefaultItems() {
		items.addAll(defaultItems);
		for (Object item : defaultItems) {
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
	public void addElement(Object item) {
		if (items.contains(itemDefaultFactory.create(item))) {
			setSelectedItem(item);
		} else {
			removeCustomElementFromEnd();
			model.insertElementAt(item, 0);
			items.add(item);
		}
	}

	private void removeCustomElementFromEnd() {
		if (getCustomItemsSize() == maximum) {
			Object item = getElementAt(getLastItemIndex());
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
	public Object getElementAt(int index) {
		return model.getElementAt(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		model.addListDataListener(l);
	}

	@Override
	public void insertElementAt(Object item, int index) {
		Object idxitem = getElementAt(index);
		if (items.contains(idxitem)) {
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
		Object item = getElementAt(index);
		if (!defaultItems.contains(item)) {
			model.removeElement(item);
			items.remove(item);
		}
	}

}
