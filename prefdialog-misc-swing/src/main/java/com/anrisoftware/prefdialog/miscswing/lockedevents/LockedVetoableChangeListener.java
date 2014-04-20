/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * The change method is not called if the listener is locked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class LockedVetoableChangeListener extends AbstractLockable implements
		VetoableChangeListener {

	/**
	 * @see #decorate(VetoableChangeListener)
	 */
	public static LockedVetoableChangeListener lockedVetoableChangeListener(
			VetoableChangeListener l) {
		return decorate(l);
	}

	/**
	 * Decorate the specified listener with locking ability.
	 * 
	 * @param l
	 *            the {@link VetoableChangeListener}.
	 * 
	 * @return the {@link LockedVetoableChangeListener}.
	 */
	public static LockedVetoableChangeListener decorate(VetoableChangeListener l) {
		return new LockedVetoableChangeListener(l);
	}

	private final VetoableChangeListener l;

	/**
	 * @see #decorate(VetoableChangeListener)
	 */
	LockedVetoableChangeListener(VetoableChangeListener l) {
		this.l = l;
	}

	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		if (!isLock()) {
			l.vetoableChange(evt);
		}
	}
}
