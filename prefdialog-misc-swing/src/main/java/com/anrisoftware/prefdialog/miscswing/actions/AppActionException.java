/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import java.util.Map;

import com.anrisoftware.globalpom.exceptions.Context;

/**
 * Error from an application action that is not recoverable.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
@SuppressWarnings("serial")
public class AppActionException extends RuntimeException {

	private final Context<AppActionException> context;

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public AppActionException(String message, Throwable cause) {
		super(message, cause);
		this.context = new Context<AppActionException>(this);
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public AppActionException(String message) {
		super(message);
		this.context = new Context<AppActionException>(this);
	}

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public AppActionException(Object message, Throwable cause) {
		super(message.toString(), cause);
		this.context = new Context<AppActionException>(this);
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public AppActionException(Object message) {
		super(message.toString());
		this.context = new Context<AppActionException>(this);
	}

	/**
	 * @see Context#addContext(String, Object)
	 */
	public AppActionException add(String name, Object value) {
		context.addContext(name, value);
		return this;
	}

	/**
	 * @see Context#addContext(String, Object)
	 */
	public AppActionException add(Object name, Object value) {
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
