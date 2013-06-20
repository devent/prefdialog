package com.anrisoftware.prefdialog.miscswing.comboboxhistory;

import java.util.Collection;

import javax.swing.MutableComboBoxModel;

/**
 * Factory to decorate a model as the history combo box model.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface HistoryComboBoxModelFactory {

	/**
	 * Decorate a model as the history combo box model.
	 * 
	 * @param model
	 *            the parent {@link MutableComboBoxModel}.
	 * 
	 * @param defaultItems
	 *            {@link Collection} of default items for the model. The default
	 *            items can not be removed.
	 * 
	 * @return the {@link HistoryComboBoxModel}
	 */
	@SuppressWarnings("rawtypes")
	HistoryComboBoxModel create(MutableComboBoxModel model,
			Collection defaultItems);
}
