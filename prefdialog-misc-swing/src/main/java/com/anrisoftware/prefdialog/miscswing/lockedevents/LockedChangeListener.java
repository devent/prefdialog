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
package com.anrisoftware.prefdialog.miscswing.lockedevents;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The change method is not called if the listener is locked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class LockedChangeListener extends AbstractLockable implements
        ChangeListener {

    /**
     * @see #decorate(ChangeListener)
     */
    public static LockedChangeListener lockedChangeListener(ChangeListener l) {
        return decorate(l);
    }

    /**
     * Decorate the specified listener with locking ability.
     * 
     * @param l
     *            the {@link ChangeListener}.
     * 
     * @return the {@link LockedChangeListener}.
     */
    public static LockedChangeListener decorate(ChangeListener l) {
        return new LockedChangeListener(l);
    }

    private final ChangeListener l;

    /**
     * @see #decorate(ChangeListener)
     */
    LockedChangeListener(ChangeListener l) {
        this.l = l;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!isLock()) {
            l.stateChanged(e);
        }
    }
}
