/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
