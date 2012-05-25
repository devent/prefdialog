package com.anrisoftware.prefdialog.dialog;

import javax.swing.JDialog;

import com.anrisoftware.prefdialog.ChildrenPanel;

/**
 * Factory to create a new {@link PreferenceDialog}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
interface PreferenceDialogFactory {

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
