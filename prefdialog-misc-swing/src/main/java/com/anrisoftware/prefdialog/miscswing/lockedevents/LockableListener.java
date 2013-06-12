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
