package com.globalscalingsoftware.prefdialog.dialog.internal;

import com.google.inject.assistedinject.Assisted;

/**
 * Use this factory to create a {@link PreferencePanelsHandler}.
 */
interface PreferencePanelsHandlerFactory {

	/**
	 * Created a new {@link PreferencePanelsHandler}.
	 * 
	 * @param preferences
	 *            the preferences object from which the handler will create all
	 *            preference panels.
	 * @return the new created {@link PreferencePanelsHandler}.
	 */
	PreferencePanelsHandler create(@Assisted Object preferences);
}
