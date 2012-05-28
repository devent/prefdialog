package com.anrisoftware.prefdialog;

/**
 * Status of the preference dialog after closing the dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public enum PreferenceDialogStatus {

	/**
	 * The user used the O.k. button to close the dialog, changes should be
	 * applied.
	 */
	OK,

	/**
	 * The user canceled the dialog, changes should be discarded.
	 */
	CANCELED,

	/**
	 * The user applied the changes in the dialog but not closed it; changes
	 * should be applied.
	 */
	APPLIED,

}
