package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

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
		install(new FactoryModuleBuilder().implement(SaveLayoutWorker.class,
				SaveLayoutWorker.class).build(SaveLayoutWorkerFactory.class));
		install(new FactoryModuleBuilder().implement(LoadLayoutWorker.class,
				LoadLayoutWorker.class).build(LoadLayoutWorkerFactory.class));
	}

}
