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

/**
 * Factory to create the add resource item undoable action.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public interface AddItemUndoableActionFactory {

    /**
     * Creates the add resource item undoable action.
     *
     * @param model
     *            the {@link DefaultListModel} of the resources.
     *
     * @param resource
     *            the resource that was added.
     *
     * @param index
     *            the index of the resources.
     *
     * @return the {@link UndoableEdit}.
     */
    UndoableEdit create(@SuppressWarnings("rawtypes") DefaultListModel model,
            Object resource, int index);
}
