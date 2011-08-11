package com.globalscalingsoftware.prefdialog.panel;

import com.globalscalingsoftware.prefdialog.annotations.Child;
import com.google.inject.assistedinject.Assisted;

interface ChildFieldHandlerWorkerFactory {

	/**
	 * Creates a new {@link ChildFieldHandlerWorker}.
	 * 
	 * @param preferences
	 *            the preferences object, need to have one field annotated with
	 *            the {@link Child} annotation.
	 * 
	 * @param panelName
	 *            the name of the preferences panel.
	 * 
	 * @return the new created {@link ChildFieldHandlerWorker}.
	 */
	ChildFieldHandlerWorker create(@Assisted Object preferences,
			@Assisted String panelName);
}
