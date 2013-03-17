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
package com.anrisoftware.prefdialog.miscswing.text.filetext;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import com.anrisoftware.globalpom.log.AbstractSerializedLogger;

/**
 * Log messages for the file text transfer handler.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FileTextTransferHandlerLogger extends AbstractSerializedLogger {

	FileTextTransferHandlerLogger() {
		super(FileTextTransferHandler.class);
	}

	void errorIO(Object handler, IOException e) {
		String message = format("Error while importing data: %s.", handler);
		logException(message, e);
	}

	void errorNotValidURISyntax(Object handler, URISyntaxException e) {
		String message = format("Invalid URI syntax: %s.", handler);
		logException(message, e);
	}

	void importFile(Object handler, File file) {
		log.debug("Import file '{}' in {}.", file, handler);
	}

}
