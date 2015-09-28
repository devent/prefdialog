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
package com.anrisoftware.prefdialog.miscswing.undomanager;

import javax.inject.Inject;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import com.google.inject.assistedinject.Assisted;

/**
 * Data edit that can be undone.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class UndoableValueEdit extends AbstractUndoableEdit {

    private final UndoableValue source;

    private final Object oldValue;

    private final Object newValue;

    private final int index;

    /**
     * @see UndoableValueEditFactory#create(UndoableValue, int, Object, Object)
     */
    @Inject
    UndoableValueEdit(@Assisted UndoableValue source, @Assisted int index,
            @Assisted("oldValue") Object oldValue,
            @Assisted("newValue") Object newValue) {
        this.source = source;
        this.index = index;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        source.redoValue(index, newValue);
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        source.undoValue(index, oldValue);
    }

}
