package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.workdockable;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.docks.api.WorkDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable.AbstractDockableLayout;
import com.google.inject.assistedinject.Assisted;

/**
 * Meta information for windows that are in the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class WorkDockableLayout extends AbstractDockableLayout<WorkDockWindow> {

	@Inject
	WorkDockableLayout(@Assisted WorkDockWindow window) {
		super(window);
	}

}
