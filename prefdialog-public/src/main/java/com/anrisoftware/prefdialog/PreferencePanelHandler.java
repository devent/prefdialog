package com.anrisoftware.prefdialog;

import java.awt.Component;

/**
 * Controls the preference panel that was created from the preferences. Use
 * {@link PreferencePanelHandlerFactory} to create a new preference panel
 * handler and add it to your component (for example a JPanel or JDialog).
 * 
 * @see PreferencePanelHandlerFactory
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
public interface PreferencePanelHandler {

	/**
	 * Creates the {@link PreferencePanelHandler preference panel handler}.
	 */
	PreferencePanelHandler createPanel();

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
	 * Checks if the input from the user is valid.
	 * 
	 * @return <code>true</code> if the input is valid or <code>false</code> if
	 *         the input is not valid.
	 */
	boolean isInputValid();

	/**
	 * Updates the UI of the panel.
	 * 
	 * Is useful to set a new Swing Look&Feel.
	 */
	void updateUI();

	/**
	 * Returns the {@link FieldHandler} that is in this panel with the given
	 * name.
	 * 
	 * @param name
	 *            the name of the field.
	 */
	FieldHandler<?> getField(String name);
}
