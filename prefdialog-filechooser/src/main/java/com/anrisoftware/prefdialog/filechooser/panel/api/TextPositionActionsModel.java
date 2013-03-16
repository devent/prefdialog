package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.awt.event.ActionListener;

/**
 * Returns the text position menu actions.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface TextPositionActionsModel {

	/**
	 * Returns the action for text only menu.
	 * 
	 * @return the {@link ActionListener}.
	 */
	ActionListener getTextOnlyAction();

	/**
	 * Returns the action for icon only menu.
	 * 
	 * @return the {@link ActionListener}.
	 */
	ActionListener getIconOnlyAction();

	/**
	 * Returns the action for text alongside icon menu.
	 * 
	 * @return the {@link ActionListener}.
	 */
	ActionListener getTextAlongsideIconAction();

}