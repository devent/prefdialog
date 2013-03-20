package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.editordockable;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable.AbstractDockableLayout;
import com.google.inject.assistedinject.Assisted;

/**
 * Meta information for docks that are in the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class EditorDockableLayout extends
		AbstractDockableLayout<EditorDockWindow> {

	@Inject
	EditorDockableLayout(@Assisted EditorDockWindow window) {
		super(window);
	}

}
