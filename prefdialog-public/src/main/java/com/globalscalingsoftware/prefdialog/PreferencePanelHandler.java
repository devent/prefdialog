package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

import javax.swing.Action;

/**
 * Controls the preference panel that was created from the preferences.
 * 
 * @see PreferencePanelHandlerFactory
 */
public interface PreferencePanelHandler {

	/**
	 * Sets the {@link Action} for the "Apply" button of the panel.
	 */
	void setApplyAction(Action a);

	/**
	 * Sets the {@link Action} for the "Restore" button of the panel.
	 */
	void setRestoreAction(Action a);

	/**
	 * Returns the {@link Component} that can be added to a frame or dialog.
	 */
	Component getAWTComponent();

	/**
	 * Restore the fields to the original values.
	 */
	void restoreInput();

	/**
	 * Apply all entered data.
	 */
	void applyInput();

	/**
	 * Returns the preferences object for this panel.
	 */
	Object getPreferences();
}
