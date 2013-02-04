package com.anrisoftware.prefdialog.filechooser.panel.core;

/**
 * Semaphore that blocks if some values are only adjusted.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class AdjustingSemaphore {

	private boolean adjusting;

	public AdjustingSemaphore() {
		this.adjusting = false;
	}

	/**
	 * Sets the semaphore in adjusting state.
	 */
	public void adjusting() {
		this.adjusting = true;
	}

	/**
	 * Removes the adjusting state of the semaphore.
	 */
	public void doneAdjusting() {
		this.adjusting = false;
	}

	/**
	 * Tests if the semaphore is in the adjusting state.
	 * 
	 * @return {@code true} if the semaphore is in the adjusting state,
	 *         {@code false} if not.
	 */
	public boolean isAdjusting() {
		return adjusting;
	}
}
