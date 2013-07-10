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
package com.anrisoftware.prefdialog.csvimportdialog.model;

import java.io.IOException;
import java.util.Map;

import com.anrisoftware.globalpom.exceptions.Context;

/**
 * Import exception.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class CsvImportException extends IOException {

	private Context<CsvImportException> context;

	/**
	 * @see IOException#IOException(String, Throwable)
	 */
	public CsvImportException(String message, Throwable cause) {
		super(message, cause);
		this.context = new Context<CsvImportException>(this);
	}

	/**
	 * @see IOException#IOException(String)
	 */
	public CsvImportException(String message) {
		super(message);
	}

	/**
	 * @see Context#addContext(String, Object)
	 */
	public CsvImportException addContext(String name, Object value) {
		return context.addContext(name, value);
	}

	/**
	 * @see Context#getContext()
	 */
	public Map<String, Object> getContext() {
		return context.getContext();
	}

	/**
	 * @see Context#toString()
	 */
	@Override
	public String toString() {
		return context.toString();
	}

}
