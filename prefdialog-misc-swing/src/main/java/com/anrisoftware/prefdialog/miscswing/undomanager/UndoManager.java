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
import javax.swing.Action;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

/**
 * Enables and disables the redo and undo menu actions.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@SuppressWarnings("serial")
public final class UndoManager extends javax.swing.undo.UndoManager {

    @Inject
    private UndoManagerLogger log;

    private Action undoAction;

    private Action redoAction;

    /**
     * Sets the undo action. It will be enabled or disabled depending if an
     * action is available to be undone.
     *
     * @param action
     *            the {@link Action}.
     */
    public void setUndoAction(Action action) {
        this.undoAction = action;
    }

    /**
     * Sets the redo action. It will be enabled or disabled depending if an
     * action is available to be redone.
     *
     * @param action
     *            the {@link Action}.
     */
    public void setRedoAction(Action action) {
        this.redoAction = action;
    }

    @Override
    public synchronized boolean addEdit(UndoableEdit edit) {
        boolean ret = super.addEdit(edit);
        if (redoAction != null) {
            redoAction.setEnabled(canRedo());
        }
        if (undoAction != null) {
            undoAction.setEnabled(canUndo());
        }
        log.addEdit(edit);
        return ret;
    }

    @Override
    public synchronized void undo() throws CannotUndoException {
        super.undo();
        if (redoAction != null) {
            redoAction.setEnabled(canRedo());
        }
        if (undoAction != null) {
            undoAction.setEnabled(canUndo());
        }
    }

    @Override
    public synchronized void redo() throws CannotRedoException {
        super.redo();
        if (redoAction != null) {
            redoAction.setEnabled(canRedo());
        }
        if (undoAction != null) {
            undoAction.setEnabled(canUndo());
        }
    }
}
