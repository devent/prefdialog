/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;

/**
 * The change method is not called if the listener is locked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class LockedChangeListener extends AbstractLockable implements
		PropertyChangeListener {

	/**
	 * @see #decorate(VetoableChangeListener)
	 */
	public static LockedChangeListener lockedChangeListener(
			PropertyChangeListener l) {
		return decorate(l);
	}

	/**
	 * Decorate the specified listener with locking ability.
	 * 
	 * @param l
	 *            the {@link PropertyChangeListener}.
	 * 
	 * @return the {@link LockedChangeListener}.
	 */
	public static LockedChangeListener decorate(PropertyChangeListener l) {
		return new LockedChangeListener(l);
	}

	private final PropertyChangeListener l;

	/**
	 * @see #decorate(PropertyChangeListener)
	 */
	LockedChangeListener(PropertyChangeListener l) {
		this.l = l;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (!isLock()) {
			l.propertyChange(evt);
		}
	}
}
