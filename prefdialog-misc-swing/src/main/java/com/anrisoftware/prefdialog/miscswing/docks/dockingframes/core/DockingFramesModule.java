package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutloader.LayoutLoaderModule;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutsaver.LayoutSaverModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Binds the Docking Frames docks.
 * 
 * @see Dock
 * @see DockFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class DockingFramesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(Dock.class,
				DockingFramesDock.class).build(DockFactory.class));
		install(new LayoutLoaderModule());
		install(new LayoutSaverModule());
	}

}
