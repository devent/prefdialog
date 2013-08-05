/*
 * Copyright 2012-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-dialog.
 *
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link SimplePropertiesDialog}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@Singleton
class SimplePropertiesDialogLogger extends AbstractLogger {

	private static final String SERVICE_FOUND1 = "No preference panel service found '{}' for dialog {}.";
	private static final String SERVICE_FOUND = "No preference panel service found";

	/**
	 * Creates a logger for {@link SimplePropertiesDialog}.
	 */
	public SimplePropertiesDialogLogger() {
		super(SimplePropertiesDialog.class);
	}

	IllegalStateException errorFindService(SimpleDialog dialog, String name) {
		return logException(new IllegalStateException(SERVICE_FOUND),
				SERVICE_FOUND1, name, dialog);
	}
}
