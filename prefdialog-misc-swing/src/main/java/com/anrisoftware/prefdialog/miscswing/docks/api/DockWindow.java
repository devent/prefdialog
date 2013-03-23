package com.anrisoftware.prefdialog.miscswing.docks.api;

import java.awt.Component;

/**
 * Child window that is docked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface DockWindow {

	/**
	 * Returns the unique identifier of the window. The identifier needs to be
	 * unique and constant for all docked windows.
	 * 
	 * @return the unique identifier of the window.
	 */
	String getId();

	/**
	 * Returns the title of the window.
	 * 
	 * @return the title of the window.
	 */
	String getTitle();

	/**
	 * Returns the AWT component of the window.
	 * 
	 * @return the {@link Component}
	 */
	Component getComponent();

	/**
	 * Returns the position of the window relative to the working area.
	 * 
	 * @return the {@link DockPosition}.
	 */
	DockPosition getPosition();

	boolean isCloseable();

	boolean isExternalizable();

	boolean isMaximizable();

	boolean isMinimizable();

	boolean isStackable();
}
