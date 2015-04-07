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
package com.anrisoftware.prefdialog.miscswing.lists;

import java.io.Serializable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang3.event.EventListenerSupport;

/**
 * Item with selected state.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class CheckListItem<E> implements Serializable {

	/**
	 * Creates a check box list item from the specified value.
	 * 
	 * @param value
	 *            the item value.
	 * 
	 * @return the {@link CheckListItem}.
	 */
	public static <E> CheckListItem<E> asItem(E value) {
		return new CheckListItem<E>(value);
	}

	private final E value;

	private final EventListenerSupport<ChangeListener> changeSupport;

	private boolean selected;

	/**
	 * Constructs the list item from the specified value.
	 * 
	 * @param value
	 *            the item value.
	 */
	public CheckListItem(E value) {
		this.changeSupport = new EventListenerSupport<ChangeListener>(
				ChangeListener.class);
		this.value = value;
		this.selected = false;
	}

	/**
	 * Returns the item value.
	 * 
	 * @return the item value.
	 */
	public E getValue() {
		return value;
	}

	/**
	 * Sets the selected state of the item.
	 * 
	 * @param selected
	 *            {@code true} if the item is selected.
	 */
	public void setSelected(boolean selected) {
		boolean oldValue = this.selected;
		this.selected = selected;
		if (oldValue != selected) {
			changeSupport.fire().stateChanged(new ChangeEvent(this));
		}
	}

	/**
	 * Returns the selected state of the item.
	 * 
	 * @return {@code true} if the item is selected.
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Adds the listener that is informed when the selected state of the item
	 * change.
	 * 
	 * @param listener
	 *            the {@link ChangeListener}.
	 */
	public void addChangeListener(ChangeListener listener) {
		changeSupport.addListener(listener);
	}

	/**
	 * Removes the listener that is informed when the selected state of the item
	 * change.
	 * 
	 * @param listener
	 *            the {@link ChangeListener}.
	 */
	public void removeChangeListener(ChangeListener listener) {
		changeSupport.removeListener(listener);
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof CheckListItem) {
			@SuppressWarnings("unchecked")
			CheckListItem<E> rhs = (CheckListItem<E>) obj;
			return value.equals(rhs.getValue());
		}
		return false;
	}
}
