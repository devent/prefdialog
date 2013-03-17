package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.singledockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.SingleDockWindow;

/**
 * Factory to create the meta information for windows that are outside of the
 * working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface SingleDockableLayoutFactory {

	/**
	 * Create the meta information for the specified window.
	 * 
	 * @param window
	 *            the {@link SingleDockWindow}.
	 * 
	 * @return the {@link SingleDockableLayout}.
	 */
	SingleDockableLayout createFor(SingleDockWindow window);
}
