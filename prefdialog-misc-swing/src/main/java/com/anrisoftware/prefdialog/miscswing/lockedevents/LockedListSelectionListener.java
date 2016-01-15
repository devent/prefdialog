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
package com.anrisoftware.prefdialog.miscswing.lockedevents;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The value changed method is not called if the listener is locked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class LockedListSelectionListener extends AbstractLockable implements
        ListSelectionListener {

    /**
     * @see #decorate(ListSelectionListener)
     */
    public static LockedListSelectionListener lockedListSelectionListener(
            ListSelectionListener l) {
        return decorate(l);
    }

    /**
     * @see #decorate(boolean, ListSelectionListener)
     */
    public static LockedListSelectionListener lockedListSelectionListener(
            boolean skipAdjusting, ListSelectionListener l) {
        return decorate(skipAdjusting, l);
    }

    /**
     * Decorate the specified listener with locking ability and skip value
     * adjusting events.
     * 
     * @param l
     *            the {@link ListSelectionListener}.
     * 
     * @return the {@link LockedListSelectionListener}.
     */
    public static LockedListSelectionListener decorate(ListSelectionListener l) {
        return new LockedListSelectionListener(true, l);
    }

    /**
     * Decorate the specified listener with locking ability.
     * 
     * @param skipAdjusting
     *            set to {@code true} to skip value adjusting events.
     * 
     * @param l
     *            the {@link ListSelectionListener}.
     * 
     * @return the {@link LockedListSelectionListener}.
     */
    public static LockedListSelectionListener decorate(boolean skipAdjusting,
            ListSelectionListener l) {
        return new LockedListSelectionListener(skipAdjusting, l);
    }

    private final ListSelectionListener l;

    private final boolean skipAdjusting;

    LockedListSelectionListener(boolean skipAdjusting, ListSelectionListener l) {
        this.skipAdjusting = skipAdjusting;
        this.l = l;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (isLock()) {
            return;
        }
        if (skipAdjusting && e.getValueIsAdjusting()) {
            return;
        }
        l.valueChanged(e);
    }
}
