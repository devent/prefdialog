package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.viewdockable;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the needed dependencies dock windows that is outside of the work
 * area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ViewDockableModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				ViewDockableFactory.class, ViewDockableFactory.class)
				.build(ViewDockableFactoryFactory.class));
		install(new FactoryModuleBuilder().implement(
				ViewDockableLayout.class, ViewDockableLayout.class).build(
				ViewDockableLayoutFactory.class));
	}

}
