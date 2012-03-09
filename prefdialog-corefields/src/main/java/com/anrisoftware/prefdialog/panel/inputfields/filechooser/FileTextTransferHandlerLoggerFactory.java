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
package com.anrisoftware.prefdialog.panel.inputfields.filechooser;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import com.anrisoftware.prefdialog.swingutils.AbstractSwingLogger;
import com.google.inject.Inject;

/**
 * Factory to create a new file text transfer handler {@link Logger}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
interface FileTextTransferHandlerLoggerFactory {

	/**
	 * Creates a new file text transfer handler {@link Logger} for the given
	 * {@link Class}.
	 */
	Logger create();

	/**
	 * Log messages for the file text transfer handler.
	 * 
	 * @author Erwin Mueller, erwin.mueller@deventm.org
	 * @since 2.1
	 */
	class Logger extends AbstractSwingLogger {

		@Inject
		Logger() {
			super(FileTextTransferHandler.class);
		}

		void errorIO(Object handler, IOException e) {
			log.error(
					format("Error while importing data in the transfer handler.",
							handler), e);
		}

		void errorNotValidURISyntax(Object handler, URISyntaxException e) {
			log.warn(
					format("Not a valid URI syntax while importing data in the transfer handler.",
							handler), e);
		}

		void importFile(Object handler, File file) {
			log.debug("Import the file ``{}'' in the transfer handler {}.",
					file, handler);
		}

	}
}
