package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable;

import static java.lang.String.format;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link AbstractDockableLayout}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class DockableLayoutLogger extends AbstractLogger {

	/**
	 * Create logger for {@link AbstractDockableLayout}.
	 */
	public DockableLayoutLogger() {
		super(AbstractDockableLayout.class);
	}

	IOException errorReadStream(Object source, ClassNotFoundException e) {
		String message = format("Error read stream in %s", source);
		IOException ex = new IOException(message, e);
		logException(message, ex);
		return ex;
	}

}
