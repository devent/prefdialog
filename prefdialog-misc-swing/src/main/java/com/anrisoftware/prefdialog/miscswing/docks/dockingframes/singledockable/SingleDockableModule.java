package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.singledockable;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the needed dependencies dock windows that is outside of the work
 * area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class SingleDockableModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				SingleDockableFactory.class, SingleDockableFactory.class)
				.build(SingleDockableFactoryFactory.class));
		install(new FactoryModuleBuilder().implement(
				SingleDockableLayout.class, SingleDockableLayout.class).build(
				SingleDockableLayoutFactory.class));
	}

}
