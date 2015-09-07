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

import static com.anrisoftware.prefdialog.miscswing.undomanager.UndoManagerLogger._.add_edit;

import javax.swing.undo.UndoableEdit;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging for {@link UndoManager}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
class UndoManagerLogger extends AbstractLogger {

    enum _ {

        add_edit("Add undoable edit {}");

        private String name;

        private _(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Sets the context of the logger to {@link UndoManager}.
     */
    public UndoManagerLogger() {
        super(UndoManager.class);
    }

    void addEdit(UndoableEdit edit) {
        debug(add_edit, edit);
    }
}
