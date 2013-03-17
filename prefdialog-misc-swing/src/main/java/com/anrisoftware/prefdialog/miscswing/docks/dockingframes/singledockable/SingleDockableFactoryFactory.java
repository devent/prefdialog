package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.singledockable;

/**
 * Factory to create the factory for a window that is outside of the working
 * area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface SingleDockableFactoryFactory {

	/**
	 * Create a new factory for a window that is outside of the working area.
	 * 
	 * @return the {@link SingleDockableFactory}.
	 */
	SingleDockableFactory create();
}
