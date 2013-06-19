package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

/**
 * Factory to decorate items as default items.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
interface ItemDefaultFactory {

	/**
	 * Create default item from the specified item.
	 * 
	 * @param item
	 *            the {@link Object} item.
	 * 
	 * @return the {@link ItemDefault}.
	 */
	ItemDefault create(Object item);
}
