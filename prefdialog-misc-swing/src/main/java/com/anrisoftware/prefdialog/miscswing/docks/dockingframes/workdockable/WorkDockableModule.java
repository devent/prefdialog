package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.workdockable;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the needed dependencies for dock windows that is in the work area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class WorkDockableModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(WorkDockableFactory.class,
				WorkDockableFactory.class).build(
				WorkDockableFactoryFactory.class));
		install(new FactoryModuleBuilder().implement(WorkDockableLayout.class,
				WorkDockableLayout.class)
				.build(WorkDockableLayoutFactory.class));
	}

}
