package com.anrisoftware.prefdialog;

import javax.swing.JPanel;

/**
 * Returns the panel for the given preference child object.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface ChildrenPanels {

	/**
	 * Returns the panel for the given preference child object.
	 * 
	 * @param panel
	 *            the {@link ChildrenPanel} that requests the child panel.
	 * 
	 * @param child
	 *            the preference child {@link Object}.
	 * 
	 * @return the {@link JPanel} of the child.
	 */
	JPanel getChildPanel(ChildrenPanel panel, Object child);

	/**
	 * Checks if the input from the user is valid.
	 * 
	 * @return <code>true</code> if the input is valid or <code>false</code> if
	 *         the input is not valid.
	 */
	boolean isInputValid();

	/**
	 * Restore all preference panel fields to the original values.
	 */
	void restoreAllInput();

	/**
	 * Apply values of all preference panel fields entered data.
	 */
	void applyAllInput();

}
