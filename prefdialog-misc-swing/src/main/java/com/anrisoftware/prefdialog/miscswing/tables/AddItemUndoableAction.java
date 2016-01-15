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

import com.google.inject.assistedinject.Assisted;

/**
 * Undo the addition of the project resource.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class AddItemUndoableAction extends AbstractUndoableEdit {

    private final DefaultListModel model;

    private final Object resource;

    private final int index;

    /**
     * @see AddItemUndoableActionFactory#create(DefaultListModel, Object, int)
     */
    @Inject
    AddItemUndoableAction(@Assisted DefaultListModel model,
            @Assisted Object resource, @Assisted int index) {
        this.model = model;
        this.resource = resource;
        this.index = index;
    }

    @Override
    public void redo() throws CannotRedoException {
        model.insertElementAt(resource, index);
        super.redo();
    }

    @Override
    public void undo() throws CannotUndoException {
        model.removeElementAt(index);
        super.undo();
    }

}
