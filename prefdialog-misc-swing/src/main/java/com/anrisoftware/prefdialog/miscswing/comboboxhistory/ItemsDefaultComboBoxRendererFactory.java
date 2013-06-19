package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import javax.swing.ListCellRenderer;

/**
 * Factory to create a render for default items of the combo box.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
interface ItemsDefaultComboBoxRendererFactory {

	/**
	 * Create the default items renderer from the parent renderer.
	 * 
	 * @param renderer
	 *            the parent {@link ListCellRenderer}.
	 * 
	 * @return the default items {@link ListCellRenderer}.
	 */
	@SuppressWarnings("rawtypes")
	ListCellRenderer create(ListCellRenderer renderer);
}
