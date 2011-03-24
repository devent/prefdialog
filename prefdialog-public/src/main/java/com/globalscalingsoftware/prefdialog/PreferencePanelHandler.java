package com.globalscalingsoftware.prefdialog;

import java.awt.Component;

import javax.swing.Action;

/**
 * Controls the preference panel that was created from the preferences. Use
 * {@link PreferencePanelHandlerFactory} to create a new preference panel
 * handler and add it to your component (for example a JPanel or JDialog).
 * 
 * @see PreferencePanelHandlerFactory
 */
public interface PreferencePanelHandler {

	/**
	 * Creates the {@link PreferencePanelHandler preference panel handler}.
	 */
	PreferencePanelHandler createPanel();

	/**
	 * Sets the {@link Action} for the "Apply" button of the panel.
	 */
	void setApplyAction(Action a);

	/**
	 * Sets the {@link Action} for the "Restore" button of the panel.
	 */
	void setRestoreAction(Action a);

	/**
	 * Returns the {@link Component} that can be added to a panel or dialog.
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

	/**
	 * Set the {@link InputChangedCallback callback} that will be called after
	 * the user inputs new data in the panel.
	 */
	void setInputChangedCallback(InputChangedCallback inputChangedCallback);

	/**
	 * Checks if the input from the user is valid.
	 * 
	 * @return <code>true</code> if the input is valid or <code>false</code> if
	 *         the input is not valid.
	 */
	boolean isInputValid();

	/**
	 * Updates the UI of the panel.
	 */
	void updateUI();
}
