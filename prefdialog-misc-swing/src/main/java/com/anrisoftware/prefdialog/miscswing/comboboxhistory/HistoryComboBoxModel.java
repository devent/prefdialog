package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import static com.google.inject.Guice.createInjector;

import java.io.Serializable;
import java.util.List;

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
	 * @see HistoryComboBoxModelFactory#create(MutableComboBoxModel)
	 */
	@SuppressWarnings("unchecked")
	public static <E> HistoryComboBoxModel<E> decorate(
			MutableComboBoxModel<E> model, List<E> defaultItems) {
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

	private final List<E> defaultItems;

	private int maximum;

	@Inject
	HistoryComboBoxModel(@Assisted MutableComboBoxModel<E> model,
			@Assisted List<E> defaultItems) {
		this.maximum = 5;
		this.model = model;
		this.defaultItems = defaultItems;
		insertDefaultItems();
	}

	private void insertDefaultItems() {
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
		if (defaultItems.contains(item)) {
			setSelectedItem(item);
		} else {
			removeCustomElementFromEnd();
			model.insertElementAt(item, 0);
		}
	}

	private void removeCustomElementFromEnd() {
		if (getCustomItemsSize() == maximum) {
			model.removeElementAt(getLastItemIndex());
		}
	}

	@Override
	public void removeElement(Object obj) {
		if (!defaultItems.contains(obj)) {
			model.removeElement(obj);
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
		if (defaultItems.contains(getElementAt(index))) {
			model.setSelectedItem(item);
		} else {
			model.insertElementAt(item, index);
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
		if (!defaultItems.contains(getElementAt(index))) {
			model.removeElementAt(index);
		}
	}

}
