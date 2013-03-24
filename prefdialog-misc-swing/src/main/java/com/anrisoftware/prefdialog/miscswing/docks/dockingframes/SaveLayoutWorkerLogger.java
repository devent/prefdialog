package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import static java.lang.String.format;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link SaveLayoutWorker}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class SaveLayoutWorkerLogger extends AbstractLogger {

	/**
	 * Create logger for {@link SaveLayoutWorker}.
	 */
	public SaveLayoutWorkerLogger() {
		super(SaveLayoutWorker.class);
	}

	void loadLayoutError(DockingFramesDock dock, String name, IOException e) {
		String message = format("Save layout '%s' error for %s.", name, dock);
		if (log.isTraceEnabled()) {
			log.error(message, e);
		} else if (log.isDebugEnabled()) {
			log.error(message);
		}
	}

	void layoutLoaded(DockingFramesDock dock, String name) {
		log.debug("Layout '{}' saved for {}.", name, dock);
	}
}
