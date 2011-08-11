package com.globalscalingsoftware.prefdialog.dialog;

/**
 * Callback that is called if the user selects a new child panel.
 */
interface ChildSelectedCallback {

	/**
	 * Called if the user selects a new child panel.
	 * 
	 * @param value
	 *            the preference value that the child panel represents.
	 */
	void call(Object value);

}
