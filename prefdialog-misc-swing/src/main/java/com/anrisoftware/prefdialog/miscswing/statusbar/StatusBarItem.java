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
package com.anrisoftware.prefdialog.miscswing.statusbar;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Queued status bar item.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
abstract class StatusBarItem implements Runnable {

    private static final String TIME = "time";

    private static final String MESSAGE = "message";

    private final int message;

    private final long time;

    protected StatusBarItem(Object message) {
        this.message = message == null ? 0 : message.hashCode();
        this.time = System.currentTimeMillis();
    }

    public int getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StatusBarItem)) {
            return false;
        }
        StatusBarItem rhs = (StatusBarItem) obj;
        return message == rhs.getMessage();
    }

    @Override
    public int hashCode() {
        return message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(MESSAGE, message)
                .append(TIME, time).toString();
    }
}
