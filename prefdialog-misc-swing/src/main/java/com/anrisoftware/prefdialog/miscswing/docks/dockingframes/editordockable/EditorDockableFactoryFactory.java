package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.editordockable;

/**
 * Factory to create the factory for a dock that is in the working area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface EditorDockableFactoryFactory {

	/**
	 * Create a new factory for a dock that is in the working area.
	 * 
	 * @return the {@link EditorDockableFactory}.
	 */
	EditorDockableFactory create();
}
