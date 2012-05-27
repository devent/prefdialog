package com.anrisoftware.prefdialog;

import javax.swing.JPanel;

/**
 * Sets the input fields for the preferences panel.
 * 
 * @see PreferencePanelFactory
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface PreferencePanel {

	/**
	 * Returns the panel with the preferences input fields.
	 * 
	 * @return the {@link JPanel} with the preferences input fields.
	 * 
	 * @since 2.2
	 */
	JPanel getPanel();

	/**
	 * Returns the preferences object.
	 * 
	 * @return the preferences {@link Object}.
	 * 
	 * @since 2.2
	 */
	Object getPreferences();

	/**
	 * Returns the name of the child preference this panel will create the input
	 * fields for.
	 * 
	 * @return the {@link String} name of the child preference.
	 * 
	 * @since 2.2
	 */
	String getChildName();

	/**
	 * Returns the field handler that is in this panel with the given name.
	 * 
	 * @param name
	 *            the name of the field.
	 * 
	 * @return the {@link FieldHandler} with the name or <code>null</code> if
	 *         there is no such field handler.
	 */
	FieldHandler<?> getField(String name);

	/**
	 * Checks if the input from the user is valid.
	 * 
	 * @return <code>true</code> if the input is valid or <code>false</code> if
	 *         the input is not valid.
	 */
	boolean isInputValid();

	/**
	 * Restore the fields to the original values.
	 */
	void restoreInput();

	/**
	 * Apply all entered data.
	 */
	void applyInput();

}
