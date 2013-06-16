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
