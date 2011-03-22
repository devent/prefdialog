package com.globalscalingsoftware.prefdialog.dialog.internal;

import com.globalscalingsoftware.prefdialog.InputChangedCallback;
import com.google.inject.assistedinject.Assisted;

/**
 * Use the factory to create new {@link CreatePreferencePanelHandlersWorker}.
 */
interface CreatePreferencePanelHandlersWorkerFactory {

	/**
	 * Creates new {@link CreatePreferencePanelHandlersWorker}.
	 * 
	 * @param preferences
	 *            the preferences object from which the handler will create all
	 *            preference panels.
	 * @param inputChangedCallback
	 *            the {@link InputChangedCallback callback} that will be called
	 *            if the user inputs data in one of the panels.
	 * @return the new created {@link CreatePreferencePanelHandlersWorker}.
	 */
	CreatePreferencePanelHandlersWorker create(@Assisted Object preferences,
			@Assisted InputChangedCallback inputChangedCallback);
}
