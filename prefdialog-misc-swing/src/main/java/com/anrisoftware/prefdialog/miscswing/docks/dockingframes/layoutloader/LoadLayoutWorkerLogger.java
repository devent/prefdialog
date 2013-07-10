/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutloader;

import static java.lang.String.format;

import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesDock;

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
