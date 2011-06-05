package com.globalscalingsoftware.prefdialog;

import java.awt.Frame;

import com.google.inject.assistedinject.Assisted;

/**
 * Factory to create a new {@link PreferenceDialogHandler}. We can use Guice to
 * create a new preferences dialog:
 * 
 * <pre>
 * handler = factory.create(owner, preferences).createDialog();
 * handler.openDialog();
 * if (handler.getOption() == OK) {
 *     compute preferences
 * }
 * </pre>
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 */
public interface PreferenceDialogHandlerFactory {

	/**
	 * Creates a new {@link PreferenceDialogHandler}.
	 * 
	 * @param owner
	 *            the {@link Frame owner} of the dialog, can be
	 *            <code>null</code>.
	 * @param preferences
	 *            the preferences bean object.
	 */
	PreferenceDialogHandler create(@Assisted Frame owner,
			@Assisted Object preferences);
}
