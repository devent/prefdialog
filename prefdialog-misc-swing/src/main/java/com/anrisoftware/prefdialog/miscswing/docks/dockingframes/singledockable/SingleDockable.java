package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.singledockable;

import java.io.Externalizable;

import com.anrisoftware.prefdialog.miscswing.docks.api.SingleDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable.AbstractDockable;

/**
 * Window that is outside of the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class SingleDockable
		extends
		AbstractDockable<SingleDockWindow, SingleDockable, SingleDockableLayout, SingleDockableFactory>
		implements SingleDockWindow, Externalizable {

	public SingleDockable(SingleDockableFactory factory, SingleDockWindow window) {
		super(factory, window);
	}

}
