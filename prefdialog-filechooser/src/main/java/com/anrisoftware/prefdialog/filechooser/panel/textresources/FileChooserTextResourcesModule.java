package com.anrisoftware.prefdialog.filechooser.panel.textresources;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FileChooserTextResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(FileChooserTextResources.class,
				FileChooserTextResources.class).build(FileChooserTextResourcesFactory.class));
	}

}
