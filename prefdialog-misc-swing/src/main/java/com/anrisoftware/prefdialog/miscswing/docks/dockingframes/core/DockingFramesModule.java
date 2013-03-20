package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.editordockable.EditorDockableModule;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable.ViewDockableModule;
import com.google.inject.AbstractModule;

/**
 * Installs the needed dependencies for Docking Frames docks.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class DockingFramesModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Dock.class).to(DockingFramesDock.class);
		install(new ViewDockableModule());
		install(new EditorDockableModule());
	}

}
