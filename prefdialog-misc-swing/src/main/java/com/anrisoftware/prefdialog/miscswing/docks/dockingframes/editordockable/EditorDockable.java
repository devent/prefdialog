package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.editordockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable.AbstractDockable;

/**
 * Editor dock that is in the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class EditorDockable
		extends
		AbstractDockable<EditorDockWindow, EditorDockable, EditorDockableLayout, EditorDockableFactory>
		implements EditorDockWindow {

	public EditorDockable(EditorDockableFactory factory, EditorDockWindow window) {
		super(factory, window);
	}

}
