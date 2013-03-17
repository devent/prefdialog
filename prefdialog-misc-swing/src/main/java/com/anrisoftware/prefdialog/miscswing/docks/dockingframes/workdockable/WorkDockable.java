package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.workdockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.WorkDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable.AbstractDockable;

/**
 * Window that is in the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class WorkDockable
		extends
		AbstractDockable<WorkDockWindow, WorkDockable, WorkDockableLayout, WorkDockableFactory>
		implements WorkDockWindow {

	public WorkDockable(WorkDockableFactory factory, WorkDockWindow window) {
		super(factory, window);
	}

}
