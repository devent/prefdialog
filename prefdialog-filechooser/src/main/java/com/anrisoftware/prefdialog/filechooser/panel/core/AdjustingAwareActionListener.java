package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Before the action performed message is done we make sure none of the
 * adjusting semaphores is in blocking state.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AdjustingAwareActionListener implements ActionListener {

	private final AdjustingSemaphore[] semaphores;

	/**
	 * Sets the semaphores that are tested before the action preformed messsage
	 * is done.
	 * 
	 * @param semaphores
	 *            the {@link AdjustingSemaphore} semaphores.
	 */
	public AdjustingAwareActionListener(AdjustingSemaphore... semaphores) {
		this.semaphores = semaphores;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (isAdjusting()) {
			return;
		}
		doAction(event);
	}

	private boolean isAdjusting() {
		for (AdjustingSemaphore s : semaphores) {
			if (s.isAdjusting()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Do the action performed message.
	 * 
	 * @param event
	 *            the {@link ActionEvent}.
	 */
	protected abstract void doAction(ActionEvent event);
}
