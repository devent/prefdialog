package com.globalscalingsoftware.prefdialog;

import com.google.inject.assistedinject.Assisted;

/**
 * A factory for {@link PreferencePanelHandler preference panel handlers}.
 * 
 * @see PreferencePanelHandler
 */
public interface PreferencePanelHandlerFactory {

	/**
	 * Creates a new {@link PreferencePanelHandler preference panel handler}.
	 * 
	 * @param preferences
	 *            the preferences from which the panel will be created.
	 * @return the new created {@link PreferencePanelHandler preference panel
	 *         handler}.
	 */
	PreferencePanelHandler create(@Assisted Object preferences);
}
