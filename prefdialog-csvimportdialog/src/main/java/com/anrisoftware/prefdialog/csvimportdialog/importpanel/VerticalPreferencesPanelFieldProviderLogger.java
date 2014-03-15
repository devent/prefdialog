/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.importpanel;

import static com.anrisoftware.prefdialog.csvimportdialog.importpanel.VerticalPreferencesPanelFieldProviderLogger._.no_service;
import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.google.inject.CreationException;
import com.google.inject.spi.Message;

/**
 * Logging messages for {@link VerticalPreferencesPanelFieldProvider}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class VerticalPreferencesPanelFieldProviderLogger extends AbstractLogger {

	enum _ {

		no_service("No '%s' field service found");

		private String name;

		private _(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	/**
	 * Creates a logger for {@link VerticalPreferencesPanelFieldProvider}.
	 */
	public VerticalPreferencesPanelFieldProviderLogger() {
		super(VerticalPreferencesPanelFieldProvider.class);
	}

	CreationException noService(Object source, String name) {
		Collection<Message> messages = new ArrayList<Message>();
		messages.add(new Message(source, format(no_service.toString(), name)));
		return logException(new CreationException(messages), no_service, name);
	}
}
