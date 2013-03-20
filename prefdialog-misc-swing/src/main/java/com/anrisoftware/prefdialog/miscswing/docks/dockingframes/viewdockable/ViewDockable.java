package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable.AbstractDockable;

/**
 * Dock that is outside of the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ViewDockable
		extends
		AbstractDockable<ViewDockWindow, ViewDockable, ViewDockableLayout, ViewDockableFactory>
		implements ViewDockWindow {

	public ViewDockable(ViewDockableFactory factory, ViewDockWindow window) {
		super(factory, window);
	}

}
