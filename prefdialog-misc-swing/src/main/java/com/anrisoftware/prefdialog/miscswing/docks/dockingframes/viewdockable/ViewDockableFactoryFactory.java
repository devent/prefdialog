package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable;

/**
 * Factory to create the factory for a dock that is outside of the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface ViewDockableFactoryFactory {

	/**
	 * Create a new factory for a dock that is outside of the working area.
	 * 
	 * @return the {@link ViewDockableFactory}.
	 */
	ViewDockableFactory create();
}
