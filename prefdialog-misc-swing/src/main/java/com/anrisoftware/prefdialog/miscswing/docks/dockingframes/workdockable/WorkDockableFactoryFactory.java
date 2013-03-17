package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.workdockable;

/**
 * Factory to create the factory for a window that is in the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface WorkDockableFactoryFactory {

	/**
	 * Create a new factory for a window that is in the working area.
	 * 
	 * @return the {@link WorkDockableFactory}.
	 */
	WorkDockableFactory create();
}
