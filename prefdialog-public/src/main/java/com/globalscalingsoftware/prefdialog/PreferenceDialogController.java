package com.globalscalingsoftware.prefdialog;

import java.awt.Frame;

import javax.swing.Action;

/**
 * The controller for the preference dialog. It is the public API for the
 * preference dialog.
 * 
 * First, the preferences and preferences start objects need to be set. Then,
 * the dialog owner (can be <code>null</code>) need to be set. After that, the
 * dialog can be open and after the dialog is closed the option the user choose
 * (o.k. or cancel) can be returned.
 * 
 * Example code:
 * 
 * <pre>
 * controller.setPreferences(preferences);
 * controller.setup(owner);
 * controller.openDialog();
 * if (controller.getOption() == OK) {
 *     compute preferences
 * }
 * 
 * </pre>
 */
public interface PreferenceDialogController {

	/**
	 * Sets the preferences object.
	 */
	void setPreferences(Object preferences);

	/**
	 * Create the dialog and all the components with the {@link Frame owner} of
	 * the dialog.
	 * 
	 * @param owner
	 *            the {@link Frame owner} of the dialog, can be
	 *            <code>null</code>.
	 */
	void setup(Frame owner);

	/**
	 * Opens the dialog.
	 */
	void openDialog();

	/**
	 * Returns the {@link Options option} which the user choose to close the
	 * dialog.
	 */
	Options getOption();

	/**
	 * Sets the {@link Action} for the "Ok" button of the dialog.
	 */
	void setOkAction(Action a);

	/**
	 * Sets the {@link Action} for the "Cancel" button of the dialog.
	 */
	void setCancelAction(Action a);

	/**
	 * Sets the {@link Action} for the "Apply" button of the dialog.
	 */
	void setApplyAction(Action a);

	/**
	 * Sets the {@link Action} for the "Restore" button of the dialog.
	 */
	void setRestoreAction(Action a);

}
