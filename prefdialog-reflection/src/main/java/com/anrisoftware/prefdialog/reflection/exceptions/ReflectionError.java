/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-reflection.
 * 
 * prefdialog-reflection is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-reflection is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-reflection. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.reflection.exceptions;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.exception.ExceptionContext;

/**
 * Error while accessing fields or methods with reflection.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
@SuppressWarnings("serial")
public class ReflectionError extends ContextedRuntimeException {

	/**
	 * No message or cause set.
	 */
	public ReflectionError() {
		super();
	}

	/**
	 * Sets the message and the cause of the error.
	 * 
	 * @param message
	 *            the message of the error.
	 * 
	 * @param cause
	 *            the {@link Throwable} cause of the error.
	 * 
	 * @param context
	 *            the {@link ExceptionContext} used to store the additional
	 *            information, {@code null} uses default implementation.
	 */
	public ReflectionError(String message, Throwable cause,
			ExceptionContext context) {
		super(message, cause, context);
	}

	/**
	 * Sets the message and the cause of the error.
	 * 
	 * @param message
	 *            the message of the error.
	 * 
	 * @param cause
	 *            the {@link Throwable} cause of the error.
	 */
	public ReflectionError(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Sets the message of the error, but with no cause.
	 * 
	 * @param message
	 *            the message of the error.
	 */
	public ReflectionError(String message) {
		super(message);
	}

	/**
	 * Set the cause of the error, but with no message.
	 * 
	 * @param cause
	 *            the {@link Throwable} cause of the error.
	 */
	public ReflectionError(Throwable cause) {
		super(cause);
	}

	@Override
	public ReflectionError addContextValue(String label, Object value) {
		return (ReflectionError) super.addContextValue(label, value);
	}
}
