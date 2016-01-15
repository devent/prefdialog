/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.tables;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.undo.UndoableEdit;

import com.anrisoftware.prefdialog.miscswing.itemswindow.ItemsWindow;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create the set items undoable action.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public interface SetItemsUndoableActionFactory {

    /**
     * Creates the set items undoable action.
     *
     * @param itemsWindow
     *            the {@link ItemsWindow}.
     *
     * @param model
     *            the {@link DefaultListModel} of the item.
     *
     * @param oldItems
     *            the {@link List} of the old items.
     *
     * @param newItems
     *            the {@link List} of the new items.
     *
     * @param indicies
     *            the {@link List} of the indicies of the items.
     *
     * @return the {@link UndoableEdit}.
     */
    @SuppressWarnings("rawtypes")
    UndoableEdit create(@Assisted ItemsWindow itemsWindow,
            @Assisted DefaultListModel model,
            @Assisted("oldResources") List oldItems,
            @Assisted("newResources") List newItems,
            @Assisted("indices") List<Integer> indicies);
}
