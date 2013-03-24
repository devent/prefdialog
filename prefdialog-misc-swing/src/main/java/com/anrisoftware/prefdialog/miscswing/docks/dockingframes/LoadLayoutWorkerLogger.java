package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import static java.lang.String.format;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link LoadLayoutWorker}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class LoadLayoutWorkerLogger extends AbstractLogger {

	/**
	 * Create logger for {@link LoadLayoutWorker}.
	 */
	public LoadLayoutWorkerLogger() {
		super(LoadLayoutWorker.class);
	}

	void loadLayoutError(DockingFramesDock dock, String name, IOException e) {
		String message = format("Load layout '%s' error for %s.", name, dock);
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
