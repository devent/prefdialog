package com.anrisoftware.prefdialog.filechooser.panel.core.docking;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class DockingModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(Docking.class,
				Docking.class).build(DockingFactory.class));
	}

}
