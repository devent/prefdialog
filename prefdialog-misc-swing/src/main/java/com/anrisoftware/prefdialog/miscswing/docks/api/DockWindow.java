package com.anrisoftware.prefdialog.miscswing.docks.api;

import java.awt.Component;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

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
	 * Returns the position of the window relative to the main window.
	 * 
	 * @return the {@link PerspectivePosition}.
	 */
	PerspectivePosition getPosition();

	/**
	 * Tests if this window matches the specified window.
	 * 
	 * @param window
	 *            the {@link DockWindow}.
	 * 
	 * @return {@code true} if it matches, {@code false} if not.
	 */
	boolean match(DockWindow window);

	/**
	 * Writes this window information to the output.
	 * 
	 * @param out
	 *            the {@link ObjectOutput}.
	 * 
	 * @throws IOException
	 *             if there was I/O error writing the information to the output.
	 */
	void writeExternal(ObjectOutput out) throws IOException;

	/**
	 * Reads this window information from the input.
	 * 
	 * @param in
	 *            the {@link ObjectInput}.
	 * 
	 * @throws IOException
	 *             if there was I/O error reading the information from the
	 *             input.
	 * 
	 * @throws ClassNotFoundException
	 *             if a needed class was not found.
	 */
	void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException;

}
