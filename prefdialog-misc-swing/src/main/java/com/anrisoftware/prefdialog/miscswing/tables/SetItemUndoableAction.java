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

import javax.inject.Inject;
import javax.swing.DefaultListModel;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import com.anrisoftware.prefdialog.miscswing.itemswindow.ItemsWindow;
import com.google.inject.assistedinject.Assisted;

/**
 * Undo value change of an item in the model.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class SetItemUndoableAction extends AbstractUndoableEdit {

    private final DefaultListModel model;

    private final ItemsWindow itemsWindow;

    private final Object oldItem;

    private final Object newItem;

    private final int index;

    /**
     * @see SetItemUndoableActionFactory#create(ItemsWindow, DefaultListModel,
     *      Object, Object, int)
     */
    @Inject
    SetItemUndoableAction(@Assisted ItemsWindow itemsWindow,
            @Assisted DefaultListModel model,
            @Assisted("oldResource") Object oldItem,
            @Assisted("newResource") Object newItem, @Assisted int index) {
        this.itemsWindow = itemsWindow;
        this.model = model;
        this.oldItem = oldItem;
        this.newItem = newItem;
        this.index = index;
    }

    @Override
    public void redo() throws CannotRedoException {
        model.setElementAt(newItem, index);
        itemsWindow.setSelectedIndex(index);
        super.redo();
    }

    @Override
    public void undo() throws CannotUndoException {
        model.setElementAt(oldItem, index);
        itemsWindow.setSelectedIndex(index);
        super.undo();
    }

}
