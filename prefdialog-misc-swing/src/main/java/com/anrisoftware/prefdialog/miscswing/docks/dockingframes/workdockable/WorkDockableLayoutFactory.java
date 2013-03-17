package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.workdockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.WorkDockWindow;

/**
 * Factory to create the meta information for windows that are in the working
 * area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface WorkDockableLayoutFactory {

	/**
	 * Create the meta information for the specified window.
	 * 
	 * @param window
	 *            the {@link WorkDockWindow}.
	 * 
	 * @return the {@link WorkDockableLayout}.
	 */
	WorkDockableLayout createFor(WorkDockWindow window);
}
