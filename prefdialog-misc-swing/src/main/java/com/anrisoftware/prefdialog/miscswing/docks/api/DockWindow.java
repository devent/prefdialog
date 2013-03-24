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

	/**
	 * Returns if the user should be able to close the dock.
	 * 
	 * @return {@code true} if the dock can be closed or {@code false} if not.
	 */
	boolean isCloseable();

	/**
	 * Returns if the user should be able to move the dock out of the main
	 * window.
	 * 
	 * @return {@code true} if the dock can be moved out or {@code false} if
	 *         not.
	 */
	boolean isExternalizable();

	/**
	 * Returns if the user should be able to maximize the dock.
	 * 
	 * @return {@code true} if the dock can be maximized or {@code false} if
	 *         not.
	 */
	boolean isMaximizable();

	/**
	 * Returns if the user should be able to minimize the dock.
	 * 
	 * @return {@code true} if the dock can be minimized or {@code false} if
	 *         not.
	 */
	boolean isMinimizable();

	/**
	 * Returns if the user should be able to stack the dock.
	 * 
	 * @return {@code true} if the dock can be stacked or {@code false} if not.
	 */
	boolean isStackable();
}
