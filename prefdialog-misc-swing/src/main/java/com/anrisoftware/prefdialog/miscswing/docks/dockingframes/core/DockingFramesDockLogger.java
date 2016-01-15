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
package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import static com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesDockLogger._.error_load_layout;
import static com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesDockLogger._.error_save_layout;
import static com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesDockLogger._.layout_loaded;
import static com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesDockLogger._.layout_saved;

import java.io.File;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutException;

/**
 * Logging messages for {@link DockingFramesDock}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class DockingFramesDockLogger extends AbstractLogger {

    enum _ {

        layout_loaded("Layout '{}' loaded from file {} for {}."),

        layout_saved("Layout '{}' saved in file {} for {}."),

        error_save_layout("Error saving layout '%s': %s"),

        error_load_layout("Error loading layout '%s': %s");

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
     * Create logger for {@link DockingFramesDock}.
     */
    public DockingFramesDockLogger() {
        super(DockingFramesDock.class);
    }

    void layoutSaved(DockingFramesDock dock, String name, File file) {
        debug(layout_saved, name, file, dock);
    }

    void layoutLoaded(DockingFramesDock dock, String name, File file) {
        debug(layout_loaded, name, file, dock);
    }

    void errorSaveLayout(String name, LayoutException e) {
        error(error_save_layout, name, e.getLocalizedMessage());
    }

    void errorLoadLayout(String name, LayoutException e) {
        error(error_load_layout, name, e.getLocalizedMessage());
    }
}
