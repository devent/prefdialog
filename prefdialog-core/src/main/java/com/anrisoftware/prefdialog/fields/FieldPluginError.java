package com.anrisoftware.prefdialog.fields;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

/**
 * Error that the field plug-in encounter an unexpected exception while
 * returning the field component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FieldPluginError extends ContextedRuntimeException {

	/**
	 * Sets the message and the cause of the error.
	 * 
	 * @param message
	 *            the message of the error.
	 * 
	 * @param cause
	 *            the {@link Throwable} cause of the error.
	 */
	public FieldPluginError(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Sets the message of the error, but with no cause.
	 * 
	 * @param message
	 *            the message of the error.
	 */
	public FieldPluginError(String message) {
		super(message);
	}

	@Override
	public FieldPluginError addContextValue(String label, Object value) {
		super.addContextValue(label, value);
		return this;
	}
}
