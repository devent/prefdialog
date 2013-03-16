package com.anrisoftware.prefdialog.filechooser.panel.imageresources;

import com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.iconsize.IconSizeActionsModule;
import com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.textposition.TextPositionActionsModule;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class FileChooserImageResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(
				FileChooserImageResources.class,
				FileChooserImageResources.class).build(
				FileChooserImageResourcesFactory.class));
		install(new TextPositionActionsModule());
		install(new IconSizeActionsModule());
	}

}
