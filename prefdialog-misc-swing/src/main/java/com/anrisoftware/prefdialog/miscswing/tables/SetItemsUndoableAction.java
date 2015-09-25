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
package com.anrisoftware.prefdialog.miscswing.tables;

import java.util.ArrayList;
import java.util.List;

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
public class SetItemsUndoableAction extends AbstractUndoableEdit {

    private final DefaultListModel model;

    private final ItemsWindow itemsWindow;

    private final List oldItems;

    private final List newItems;

    private final List<Integer> indicies;

    /**
     * @see SetItemsUndoableActionFactory#create(ItemsWindow, DefaultListModel,
     *      List, List, List)
     */
    @Inject
    SetItemsUndoableAction(@Assisted ItemsWindow itemsWindow,
            @Assisted DefaultListModel model,
            @Assisted("oldResources") List oldItems,
            @Assisted("newResources") List newItems,
            @Assisted("indices") List<Integer> indicies) {
        this.itemsWindow = itemsWindow;
        this.model = model;
        this.oldItems = new ArrayList(oldItems);
        this.newItems = new ArrayList(newItems);
        this.indicies = new ArrayList(indicies);
    }

    @Override
    public void redo() throws CannotRedoException {
        for (int i = 0; i < newItems.size(); i++) {
            model.setElementAt(newItems.get(i), indicies.get(i));
        }
        itemsWindow.setSelectedIndex(indicies.get(indicies.size() - 1));
        super.redo();
    }

    @Override
    public void undo() throws CannotUndoException {
        for (int i = 0; i < oldItems.size(); i++) {
            model.setElementAt(oldItems.get(i), indicies.get(i));
        }
        itemsWindow.setSelectedIndex(indicies.get(indicies.size() - 1));
        super.undo();
    }

}
