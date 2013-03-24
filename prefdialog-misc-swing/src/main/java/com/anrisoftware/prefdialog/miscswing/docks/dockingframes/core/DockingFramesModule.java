package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutloader.LayoutLoaderModule;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutsaver.LayoutSaverModule;
import com.google.inject.AbstractModule;

/**
 * Binds the Docking Frames docks.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class DockingFramesModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Dock.class).to(DockingFramesDock.class);
		install(new LayoutLoaderModule());
		install(new LayoutSaverModule());
	}

}
