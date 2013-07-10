/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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

import java.io.Serializable;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

/**
 * Decorates an item as the default item for the renderer.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ItemDefault implements Serializable {

	private final Object item;

	/**
	 * @see ItemDefaultFactory#create(Object)
	 */
	@Inject
	ItemDefault(@Assisted Object item) {
		this.item = item;
	}

	public Object getItem() {
		return item;
	}

	@Override
	public int hashCode() {
		return item.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof ItemDefault) {
			ItemDefault rhs = (ItemDefault) obj;
			return item.equals(rhs.item);
		}
		return item.equals(obj);
	}

	@Override
	public String toString() {
		return item.toString();
	}

}
