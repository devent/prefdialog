package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;

/**
 * Factory to create the meta information for docks that are outside of the
 * working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface ViewDockableLayoutFactory {

	/**
	 * Create the meta information for the specified dock.
	 * 
	 * @param window
	 *            the {@link ViewDockWindow}.
	 * 
	 * @return the {@link ViewDockableLayout}.
	 */
	ViewDockableLayout createFor(ViewDockWindow window);
}
