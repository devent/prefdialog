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
package com.anrisoftware.prefdialog.miscswing.statusbar;

import static com.anrisoftware.prefdialog.miscswing.statusbar.StatusBarWorkerLogger._.add_item;
import static com.anrisoftware.prefdialog.miscswing.statusbar.StatusBarWorkerLogger._.add_item_old;
import static com.anrisoftware.prefdialog.miscswing.statusbar.StatusBarWorkerLogger._.end_item;
import static com.anrisoftware.prefdialog.miscswing.statusbar.StatusBarWorkerLogger._.item_set;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging for {@link StatusBarWorker}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class StatusBarWorkerLogger extends AbstractLogger {

    enum _ {

        add_item("Add status bar item {}."),

        item_set("Set status bar item {}."),

        end_item("Took end status bar item {}."),

        add_item_old("Add status bar item {} - {}");

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
     * Sets the context of the logger to {@link StatusBarWorker}.
     */
    public StatusBarWorkerLogger() {
        super(StatusBarWorker.class);
    }

    void addItem(StatusBarItem item) {
        debug(add_item, item);
    }

    void itemSet(StatusBarItem item) {
        debug(item_set, item);
    }

    void timerEnd(StatusBarItem item) {
        debug(end_item, item);
    }

    void addItem(StatusBarItem oldItem, StatusBarItem item) {
        debug(add_item_old, oldItem, item);
    }
}
