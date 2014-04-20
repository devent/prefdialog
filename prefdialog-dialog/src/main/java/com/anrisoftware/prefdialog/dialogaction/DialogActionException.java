/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.dialogaction;

import java.util.Map;

import com.anrisoftware.globalpom.exceptions.Context;

/**
 * Throws error while creating the dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class DialogActionException extends RuntimeException {

	private final Context<DialogActionException> context;

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 */
	public DialogActionException(String message, Throwable cause) {
		super(message, cause);
		this.context = new Context<DialogActionException>(this);
	}

	/**
	 * @see RuntimeException#RuntimeException(String)
	 */
	public DialogActionException(String message) {
		super(message);
		this.context = new Context<DialogActionException>(this);
	}

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 */
	public DialogActionException(Object message, Throwable cause) {
		super(message.toString(), cause);
		this.context = new Context<DialogActionException>(this);
	}

	/**
	 * @see RuntimeException#RuntimeException(String)
	 */
	public DialogActionException(Object message) {
		super(message.toString());
		this.context = new Context<DialogActionException>(this);
	}

	/**
	 * @see Context#addContext(String, Object)
	 */
	public DialogActionException add(String name, Object value) {
		context.addContext(name, value);
		return this;
	}

	/**
	 * @see Context#addContext(String, Object)
	 */
	public DialogActionException add(Object name, Object value) {
		context.addContext(name.toString(), value);
		return this;
	}

	/**
	 * @see Context#getContext()
	 */
	public Map<String, Object> getContext() {
		return context.getContext();
	}

	@Override
	public String toString() {
		return context.toString();
	}
}
