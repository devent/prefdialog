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

import static java.util.Arrays.copyOf;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.swing.DefaultListModel;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import com.google.inject.assistedinject.Assisted;

/**
 * Undo the deletion of project resources.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class RemoveItemsUndoableAction extends AbstractUndoableEdit {

    private final List resources;

    private final int[] indices;

    private final DefaultListModel model;

    /**
     * @see RemoveItemsUndoableActionFactory#create(DefaultListModel, List,
     *      int[])
     */
    @Inject
    RemoveItemsUndoableAction(@Assisted DefaultListModel model,
            @Assisted List resources, @Assisted int[] indices) {
        this.model = model;
        this.resources = new ArrayList(resources);
        this.indices = copyOf(indices, indices.length);
    }

    @Override
    public void redo() throws CannotRedoException {
        redo0();
        super.redo();
    }

    @Override
    public void undo() throws CannotUndoException {
        undo0();
        super.undo();
    }

    private void redo0() {
        for (int i : indices) {
            model.removeElementAt(i);
        }
    }

    private void undo0() {
        for (int i = 0; i < resources.size(); i++) {
            Object res = resources.get(i);
            int index = indices[i];
            model.insertElementAt(res, index);
        }
    }

}
