package com.anrisoftware.prefdialog.panel;

import com.anrisoftware.prefdialog.annotations.Child;

/**
 * Factory to create a new {@link ChildFieldWorker}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
interface ChildFieldWorkerFactory {

	/**
	 * Creates a new {@link ChildFieldWorker}.
	 * 
	 * @param preferences
	 *            the preferences object, need to have one field annotated with
	 *            the {@link Child} annotation.
	 * 
	 * @param childName
	 *            the name of the child preference this panel will create the
	 *            child field handler.
	 */
	ChildFieldWorker create(Object preferences, String childName);
}
