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

/**
 * Lock listener so that the methods are not called until the listener is
 * unlocked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface LockableListener {

	/**
	 * Lock the listener.
	 */
	void lock();

	/**
	 * Unlock the listener.
	 */
	void unlock();

	/**
	 * Set the lock of the listener.
	 * 
	 * @param lock
	 *            {@code true} to lock the listener.
	 */
	void setLock(boolean lock);

	/**
	 * Returns the lock of the listener.
	 * 
	 * @return {@code true} if the listener is locked.
	 */
	boolean isLock();
}
