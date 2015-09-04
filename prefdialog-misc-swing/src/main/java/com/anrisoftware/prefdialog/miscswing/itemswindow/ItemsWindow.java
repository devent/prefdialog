/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of gsclock-main. All rights reserved.
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
