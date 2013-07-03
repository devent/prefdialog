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
	public static LockedChangeListener lockedVetoableChangeListener(
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
