package com.anrisoftware.prefdialog.miscswing.docks.layouts.dockingframes;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;

/**
 * Logging messages for {@link DefaultLayoutTask}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class DefaultLayoutTaskLogger extends AbstractLogger {

	private static final String ADD_EDITOR_INTERRUPTED_MESSAGE = "Add editor '%s' interrupted in %s.";
	private static final String ADD_EDITOR_ERROR_MESSAGE = "Add editor '%s' error in %s.";

	/**
	 * Create logger for {@link DefaultLayoutTask}.
	 */
	public DefaultLayoutTaskLogger() {
		super(DefaultLayoutTask.class);
	}

	void addEditorInterrupted(DefaultLayoutTask layout, InterruptedException e,
			EditorDockWindow dock) {
		logException(e, ADD_EDITOR_INTERRUPTED_MESSAGE, dock, layout);
	}

	void addEditorError(DefaultLayoutTask layout, Throwable e,
			EditorDockWindow dock) {
		logException(e, ADD_EDITOR_ERROR_MESSAGE, dock, layout);
	}
}
