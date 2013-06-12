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
	 * Decorate the specified listener with locking ability.
	 * 
	 * @param l
	 *            the {@link ListSelectionListener}.
	 * 
	 * @return the {@link LockedListSelectionListener}.
	 */
	public static LockedListSelectionListener decorate(ListSelectionListener l) {
		return new LockedListSelectionListener(l);
	}

	private final ListSelectionListener l;

	LockedListSelectionListener(ListSelectionListener l) {
		this.l = l;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!isLock()) {
			l.valueChanged(e);
		}
	}
}
