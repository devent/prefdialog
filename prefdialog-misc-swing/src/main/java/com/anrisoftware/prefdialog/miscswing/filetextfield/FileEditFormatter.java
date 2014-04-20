/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
import java.text.ParseException;

import javax.swing.text.DefaultFormatter;

/**
 * Custom edit formatter for a file field. It will show the whole path of the
 * file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
class FileEditFormatter extends DefaultFormatter {

	@Override
	public boolean getOverwriteMode() {
		return false;
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value == null) {
			return "";
		}
		String path = createPath((File) value);
		return path;
	}

	private String createPath(File value) {
		String path = value.getAbsolutePath();
		return path;
	}

	@Override
	public Object stringToValue(String string) throws ParseException {
		return new File(string);
	}
}
