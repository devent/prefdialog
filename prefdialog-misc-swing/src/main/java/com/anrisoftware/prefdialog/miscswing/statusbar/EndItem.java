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

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Status bar item that marks the end of the queue.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class EndItem extends StatusBarItem {

    /**
     * Instance of the end of queue item.
     */
    public static final StatusBarItem endItem = new EndItem();

    private static final String TIME = "time";

    private EndItem() {
        super(null);
    }

    @Override
    public void run() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(TIME, getTime()).toString();
    }
}
