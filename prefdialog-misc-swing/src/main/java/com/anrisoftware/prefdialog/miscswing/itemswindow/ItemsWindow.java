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
package com.anrisoftware.prefdialog.miscswing.itemswindow;

import java.util.List;

/**
 * Window that have items that can be selected, added and removed.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public interface ItemsWindow<ItemType> {

    /**
     * Stops the editing.
     */
    void stopEditing();

    /**
     * Sets the selected items.
     *
     * @param indices
     *            the indices of the items.
     */
    void setSelectedIndices(int[] indices);

    /**
     * Sets the selected item.
     *
     * @param index
     *            the {@link Integer} index of the item.
     */
    void setSelectedIndex(int index);

    /**
     * Returns the selected index.
     *
     * @return the {@link Integer} index or {@code -1} if no item is selected.
     */
    int getSelectedIndex();

    /**
     * Returns the current selected items.
     *
     * @return the {@link List} of the items.
     */
    List<ItemType> getSelectedItems();

    /**
     * Returns the indices of the current selected items.
     *
     * @return the indices.
     */
    int[] getSelectedIndices();

    /**
     * Selects all items.
     */
    void selectAll();

    /**
     * Clears the selection.
     */
    void clearSelection();

}
