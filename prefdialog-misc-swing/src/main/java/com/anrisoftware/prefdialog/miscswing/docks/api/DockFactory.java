package com.anrisoftware.prefdialog.miscswing.docks.api;

import javax.swing.JFrame;

/**
 * Factory for dock layout.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface DockFactory {

	/**
	 * Creates the dock without a main window specified.
	 * 
	 * @return the {@link Dock}.
	 */
	Dock create();

	/**
	 * Creates the dock.
	 * 
	 * @param frame
	 *            the main window {@link JFrame}.
	 * 
	 * @return the {@link Dock}.
	 */
	Dock create(JFrame frame);
}
