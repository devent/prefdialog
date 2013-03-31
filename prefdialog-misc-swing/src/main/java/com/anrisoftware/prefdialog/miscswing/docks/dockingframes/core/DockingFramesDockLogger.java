package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

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

	private static final String LAYOUT_LOADED = "Layout '{}' loaded from file {} for {}.";
	private static final String LOAD_ERROR_MESSAGE = "Load layout '%s' error.";
	private static final String LOAD_ERROR = "Load layout '%s' error for %s.";
	private static final String LOAD_INTERRUPED_MESSAGE = "Load layout '%s' interruped.";
	private static final String LOAD_INTERRUPED = "Load layout '%s' interruped for %s.";
	private static final String LAYOUT_SAVED = "Layout '{}' saved in file {} for {}.";
	private static final String SAVE_ERROR_MESSAGE = "Save layout '%s' error.";
	private static final String SAVE_ERROR = "Save layout '%s' error for %s.";
	private static final String SAVE_INTERRUPED_MESSAGE = "Save layout '%s' interruped.";
	private static final String SAVE_INTERRUPED = "Save layout '%s' interruped for %s.";

	/**
	 * Create logger for {@link DockingFramesDock}.
	 */
	public DockingFramesDockLogger() {
		super(DockingFramesDock.class);
	}

	IOException saveLayoutInterrupted(DockingFramesDock dock, String name) {
		return logException(
				new IOException(format(SAVE_INTERRUPED, name, dock)),
				SAVE_INTERRUPED_MESSAGE, name);
	}

	IOException saveLayoutError(DockingFramesDock dock, String name,
			Throwable cause) {
		return logException(new IOException(format(SAVE_ERROR, name, dock),
				cause), SAVE_ERROR_MESSAGE, name);
	}

	void layoutSaved(DockingFramesDock dock, String name, File file) {
		log.debug(LAYOUT_SAVED, name, file, dock);
	}

	IOException loadLayoutInterrupted(DockingFramesDock dock, String name) {
		return logException(
				new IOException(format(LOAD_INTERRUPED, name, dock)),
				LOAD_INTERRUPED_MESSAGE, name);
	}

	IOException loadLayoutError(DockingFramesDock dock, String name,
			Throwable cause) {
		return logException(new IOException(format(LOAD_ERROR, name, dock),
				cause), LOAD_ERROR_MESSAGE, name);
	}

	void layoutLoaded(DockingFramesDock dock, String name, File file) {
		log.debug(LAYOUT_LOADED, name, file, dock);
	}

}
