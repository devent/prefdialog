package com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.iconsize;

import com.anrisoftware.prefdialog.filechooser.panel.api.IconSizeActionsModel;
import com.google.inject.AbstractModule;

/**
 * Binds the icon size actions model implementation.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class IconSizeActionsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IconSizeActionsModel.class).to(IconSizeActionsModelImpl.class);
	}

}
