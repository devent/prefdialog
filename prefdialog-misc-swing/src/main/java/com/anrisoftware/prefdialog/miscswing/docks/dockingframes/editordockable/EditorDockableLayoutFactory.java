package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.editordockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;

/**
 * Factory to create the meta information for docks that are in the working
 * area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface EditorDockableLayoutFactory {

	/**
	 * Create the meta information for the specified dock.
	 * 
	 * @param window
	 *            the {@link EditorDockWindow}.
	 * 
	 * @return the {@link EditorDockableLayout}.
	 */
	EditorDockableLayout createFor(EditorDockWindow window);
}
