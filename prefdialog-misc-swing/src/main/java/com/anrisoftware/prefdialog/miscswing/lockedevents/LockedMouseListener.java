package com.anrisoftware.prefdialog.miscswing.lockedevents;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionListener;

/**
 * The mouse listener methods are not called if the listener is locked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class LockedMouseListener extends AbstractLockable implements
		LockableListener, MouseListener {

	/**
	 * @see #decorate(ListSelectionListener)
	 */
	public static LockedMouseListener lockedMouseListener(MouseListener l) {
		return decorate(l);
	}

	/**
	 * Decorate the specified listener with locking ability.
	 * 
	 * @param l
	 *            the {@link MouseListener}.
	 * 
	 * @return the {@link LockedMouseListener}.
	 */
	public static LockedMouseListener decorate(MouseListener l) {
		return new LockedMouseListener(l);
	}

	private final MouseListener l;

	LockedMouseListener(MouseListener l) {
		this.l = l;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!isLock()) {
			l.mouseClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!isLock()) {
			l.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!isLock()) {
			l.mouseReleased(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (!isLock()) {
			l.mouseEntered(e);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!isLock()) {
			l.mouseExited(e);
		}
	}

}
