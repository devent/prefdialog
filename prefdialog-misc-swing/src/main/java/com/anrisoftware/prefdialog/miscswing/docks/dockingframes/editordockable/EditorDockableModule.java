package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.editordockable;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * Installs the needed dependencies for dock windows that is in the work area.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class EditorDockableModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(EditorDockableFactory.class,
				EditorDockableFactory.class).build(
				EditorDockableFactoryFactory.class));
		install(new FactoryModuleBuilder().implement(EditorDockableLayout.class,
				EditorDockableLayout.class)
				.build(EditorDockableLayoutFactory.class));
	}

}
