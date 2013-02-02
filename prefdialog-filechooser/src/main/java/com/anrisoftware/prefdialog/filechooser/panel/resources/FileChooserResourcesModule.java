package com.anrisoftware.prefdialog.filechooser.panel.resources;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FileChooserResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(ImageResources.class,
				ImageResources.class).build(ImageResourcesFactory.class));
	}

}
