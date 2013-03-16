package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.awt.event.ActionListener;

import com.anrisoftware.resources.images.api.IconSize;

/**
 * Returns the icon size menu actions.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface IconSizeActionsModel {

	/**
	 * Sets the default icon size.
	 * 
	 * @param size
	 *            the default {@link IconSize}.
	 */
	void setDefaultIconSize(IconSize size);

	ActionListener getHugeSizeAction();

	ActionListener getLargeSizeAction();

	ActionListener getMediumSizeAction();

	ActionListener getSmallSizeAction();

	ActionListener getDefaultSizeAction();

}
