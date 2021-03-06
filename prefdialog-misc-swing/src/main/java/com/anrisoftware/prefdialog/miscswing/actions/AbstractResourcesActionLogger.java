/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.actions;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link AbstractResourcesAction}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class AbstractResourcesActionLogger extends AbstractLogger {

	private static final String NO_RESOURCE = "No resource '{}_{}'.";

	/**
	 * Creates a logger for {@link AbstractResourcesAction}.
	 */
	public AbstractResourcesActionLogger() {
		super(AbstractResourcesAction.class);
	}

	boolean checkResource(AbstractResourcesAction action, Object resource,
			String suffix) {
		if (resource == null) {
			log.debug(NO_RESOURCE, action.getName(), suffix);
			return false;
		} else {
			return true;
		}
	}

}
