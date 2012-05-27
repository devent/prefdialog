/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.panel;

import java.lang.reflect.Field;

import com.anrisoftware.prefdialog.swingutils.AbstractSwingLogger;

/**
 * Logging messages for {@link ChildFieldWorker}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class ChildFieldWorkerLogger extends AbstractSwingLogger {

	/**
	 * Creates a new logger for {@link ChildFieldWorker}.
	 */
	ChildFieldWorkerLogger() {
		super(ChildFieldWorker.class);
	}

	void fieldHandlerAdded(Object fieldHandler) {
		log.debug("Add new field handler {}.", fieldHandler);
	}

	void discoveredChildAnnotationForPanel(String panelName, Object value) {
		log.debug(
				"Discovered child annotation for the panel ``{}'' in ``{}''.",
				panelName, value);
	}

	void creatingNewChild(Field field, Object value) {
		log.debug(
				"Creating a new child field handler for the field {} with the value ``{}''.",
				field, value);
	}

	void setupNewGroup(Object handler) {
		log.debug("Setup new group field handler {}.", handler);
	}

}
