package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link DockingFramesDock}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class DockingFramesDockLogger extends AbstractLogger {

	/**
	 * Create logger for {@link DockingFramesDock}.
	 */
	public DockingFramesDockLogger() {
		super(DockingFramesDock.class);
	}

	IOException saveLayoutInterrupted(DockingFramesDock dock, String name) {
		IOException ex = new IOException(format(
				"Save layout '%s' interruped for %s.", name, dock));
		logException(format("Save layout '%s' interruped.", name, dock), ex);
		return ex;
	}

	IOException saveLayoutError(DockingFramesDock dock, String name,
			Throwable cause) {
		IOException ex = new IOException(format(
				"Save layout '%s' error for %s.", name, dock), cause);
		logException(format("Save layout '%s' error.", name), ex);
		return ex;
	}

	void layoutSaved(DockingFramesDock dock, String name, File file) {
		if (log.isDebugEnabled()) {
			log.debug("Layout '{}' saved in file {} for {}.", new Object[] {
					name, file, dock });
		}
	}

	IOException loadLayoutInterrupted(DockingFramesDock dock, String name) {
		IOException ex = new IOException(format(
				"Load layout '%s' interruped for %s.", name, dock));
		logException(format("Load layout '%s' interruped.", name, dock), ex);
		return ex;
	}

	IOException loadLayoutError(DockingFramesDock dock, String name,
			Throwable cause) {
		IOException ex = new IOException(format(
				"Load layout '%s' error for %s.", name, dock), cause);
		logException(format("Load layout '%s' error.", name), ex);
		return ex;
	}

	void layoutLoaded(DockingFramesDock dock, String name, File file) {
		if (log.isDebugEnabled()) {
			log.debug("Layout '{}' loaded from file {} for {}.", new Object[] {
					name, file, dock });
		}
	}

}
