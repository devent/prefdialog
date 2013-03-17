package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.singledockable;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.docks.api.SingleDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable.AbstractDockableLayout;
import com.google.inject.assistedinject.Assisted;

/**
 * Meta information for windows that are outside of the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class SingleDockableLayout extends
		AbstractDockableLayout<SingleDockWindow> {

	@Inject
	SingleDockableLayout(@Assisted SingleDockWindow window) {
		super(window);
	}

}
