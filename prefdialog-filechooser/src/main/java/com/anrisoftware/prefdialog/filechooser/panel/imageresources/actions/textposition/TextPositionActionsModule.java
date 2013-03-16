package com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.textposition;

import com.anrisoftware.prefdialog.filechooser.panel.api.TextPositionActionsModel;
import com.google.inject.AbstractModule;

/**
 * Binds the text position actions model implementation.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class TextPositionActionsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TextPositionActionsModel.class).to(
				TextPositionActionsModelImpl.class);
	}

}
