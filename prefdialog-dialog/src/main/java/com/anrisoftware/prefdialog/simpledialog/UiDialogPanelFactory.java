package com.anrisoftware.prefdialog.simpledialog;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Factory to create the dialog panel inside the AWT thread.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
interface UiDialogPanelFactory {

	/**
	 * Creates the dialog panel.
	 * 
	 * @return the {@link UiDialogPanel}.
	 */
	@OnAwt
	UiDialogPanel create();
}
