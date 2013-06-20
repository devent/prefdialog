package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;
import javax.swing.MutableComboBoxModel;

/**
 * Factory to decorate a combo box as the history combo box. That is a combo box
 * that retains a history of added items in the model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
public interface HistoryComboBoxFactory {

	/**
	 * @see #create(JComboBox, MutableComboBoxModel, ListCellRenderer,
	 *      Collection)
	 */
	HistoryComboBox create(JComboBox comboBox);

	/**
	 * @see #create(JComboBox, MutableComboBoxModel, ListCellRenderer,
	 *      Collection)
	 */
	HistoryComboBox create(JComboBox comboBox, Collection defaultItems);

	/**
	 * @see #create(JComboBox, MutableComboBoxModel, ListCellRenderer,
	 *      Collection)
	 */
	HistoryComboBox create(JComboBox comboBox, MutableComboBoxModel model,
			Collection defaultItems);

	/**
	 * Decorate the combo box as the history combo box.
	 * 
	 * @param comboBox
	 *            the {@link JComboBox}.
	 * 
	 * @param model
	 *            the {@link MutableComboBoxModel} model.
	 * 
	 * @param renderer
	 *            the {@link ListCellRenderer} renderer.
	 * 
	 * @param defaultItems
	 *            {@link Collection} of default items for the model. The default
	 *            items can not be removed.
	 * 
	 * @return the {@link HistoryComboBox}.
	 */
	HistoryComboBox create(JComboBox comboBox, MutableComboBoxModel model,
			ListCellRenderer renderer, Collection defaultItems);
}
