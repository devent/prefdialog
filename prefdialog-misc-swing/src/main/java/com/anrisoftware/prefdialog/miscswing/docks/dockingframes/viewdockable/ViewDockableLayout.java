package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable.AbstractDockableLayout;
import com.google.inject.assistedinject.Assisted;

/**
 * Meta information for docks that are outside of the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ViewDockableLayout extends
		AbstractDockableLayout<ViewDockWindow> {

	@Inject
	ViewDockableLayout(@Assisted ViewDockWindow window) {
		super(window);
	}

}
