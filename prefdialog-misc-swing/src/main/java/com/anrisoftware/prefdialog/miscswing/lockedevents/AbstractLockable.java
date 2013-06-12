package com.anrisoftware.prefdialog.miscswing.lockedevents;

/**
 * Sets the lock of the listener.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public abstract class AbstractLockable implements LockableListener {

	private boolean lock;

	@Override
	public boolean isLock() {
		return lock;
	}

	@Override
	public void unlock() {
		setLock(false);
	}

	@Override
	public void lock() {
		setLock(true);
	}

	@Override
	public void setLock(boolean lock) {
		this.lock = lock;
	}
}
