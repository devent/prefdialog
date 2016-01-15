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

import javax.swing.DefaultListModel;
import javax.swing.undo.UndoableEdit;

import com.anrisoftware.prefdialog.miscswing.itemswindow.ItemsWindow;
import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create the set item undoable action.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public interface SetItemUndoableActionFactory {

    /**
     * Creates the set item undoable action.
     *
     * @param itemsWindow
     *            the {@link ItemsWindow}.
     *
     * @param model
     *            the {@link DefaultListModel} of the item.
     *
     * @param oldItem
     *            the old item.
     *
     * @param newItem
     *            the new item.
     *
     * @param index
     *            the index of the item.
     *
     * @return the {@link UndoableEdit}.
     */
    @SuppressWarnings("rawtypes")
    UndoableEdit create(@Assisted ItemsWindow itemsWindow,
            @Assisted DefaultListModel model,
            @Assisted("oldResource") Object oldItem,
            @Assisted("newResource") Object newItem, @Assisted int index);
}
