package com.anrisoftware.prefdialog;

import javax.swing.JDialog;

/**
 * Factory to create a new {@link PreferenceDialog}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public interface PreferenceDialogFactory {

	/**
	 * Creates a new {@link PreferenceDialog}.
	 * 
	 * @param dialog
	 *            the {@link JDialog} to use for the components.
	 * 
	 * @param childrenPanel
	 *            the {@link ChildrenPanel} that contains a list of the children
	 *            and the selected child panel.
	 */
	PreferenceDialog create(JDialog dialog, ChildrenPanel childrenPanel);
}
