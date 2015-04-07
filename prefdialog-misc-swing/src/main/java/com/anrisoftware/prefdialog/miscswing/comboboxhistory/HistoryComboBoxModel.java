/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import static java.util.Collections.synchronizedSet;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
	 * @see HistoryComboBoxModelFactory#create(MutableComboBoxModel, Collection)
	 */
	@Inject
	HistoryComboBoxModel(ItemDefaultFactory itemDefaultFactory,
			@Assisted MutableComboBoxModel model,
			@Assisted Collection defaultItems) {
		this.maximum = 5;
		this.model = model;
		this.itemDefaultFactory = itemDefaultFactory;
		Collection<Object> defaultItemsCollection = convertDefaultItems(defaultItems);
		this.defaultItems = new HashSet<Object>(defaultItemsCollection);
		this.items = synchronizedSet(fromModel(model));
		insertDefaultItems(defaultItemsCollection);
		setSelectedItem(model.getSelectedItem());
	}

	private Collection<Object> convertDefaultItems(Collection items) {
		List<Object> set = new ArrayList<Object>();
		for (Object item : items) {
			set.add(itemDefaultFactory.create(item));
		}
		return set;
	}

	private Set<Object> fromModel(MutableComboBoxModel model) {
		Set<Object> set = new HashSet<Object>(model.getSize());
		for (int i = 0; i < model.getSize(); i++) {
			set.add(model.getElementAt(i));
		}
		return set;
	}

	private void insertDefaultItems(Collection defaultItems) {
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
			if (item instanceof String && isEmpty((String) item)) {
				return;
			}
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
			if (item instanceof String && isEmpty((String) item)) {
				return;
			}
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
