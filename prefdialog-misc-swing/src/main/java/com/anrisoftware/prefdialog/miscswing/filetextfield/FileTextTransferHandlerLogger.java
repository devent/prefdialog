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
package com.anrisoftware.prefdialog.miscswing.filetextfield;

import java.io.File;
import java.io.IOException;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.globalpom.resources.ConvertException;

/**
 * Log messages for the file text transfer handler.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class FileTextTransferHandlerLogger extends AbstractLogger {

	private static final String IMPORT_FILE = "Import file '{}' in {}.";
	private static final String INVALID_URI_SYNTAX = "Invalid URI syntax: {}.";
	private static final String ERROR_IMPORTING = "Error while importing data: {}.";

	FileTextTransferHandlerLogger() {
		super(FileTextTransferHandler.class);
	}

	void errorIO(Object handler, IOException e) {
		logException(e, ERROR_IMPORTING, handler);
	}

	void errorNotValidURISyntax(Object handler, ConvertException e) {
		logException(e, INVALID_URI_SYNTAX, handler);
	}

	void importFile(Object handler, File file) {
		log.debug(IMPORT_FILE, file, handler);
	}

}
