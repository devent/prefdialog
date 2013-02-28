package com.anrisoftware.prefdialog.filechooser.panel.resources;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FileChooserImageResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(FileChooserImageResources.class,
				FileChooserImageResources.class).build(FileChooserImageResourcesFactory.class));
	}

}
