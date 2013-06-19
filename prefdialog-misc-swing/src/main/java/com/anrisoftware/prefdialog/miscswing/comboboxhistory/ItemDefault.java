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
class ItemDefault implements Serializable {

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
