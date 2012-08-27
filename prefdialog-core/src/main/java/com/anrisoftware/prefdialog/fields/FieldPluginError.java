package com.anrisoftware.prefdialog.fields;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.replace;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.commons.lang3.exception.ExceptionContext;

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
	 * No message or cause set.
	 */
	public FieldPluginError() {
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
	public FieldPluginError(String message, Throwable cause,
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

	/**
	 * Set the cause of the error, but with no message.
	 * 
	 * @param cause
	 *            the {@link Throwable} cause of the error.
	 */
	public FieldPluginError(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		Throwable cause = getCause();
		return cause == null ? getMessageNoCause() : getMessageCause();
	}

	private String getMessageNoCause() {
		String message = super.getMessage();
		return message;
	}

	private String getMessageCause() {
		String message = super.getMessage();
		String causeMessage = appendDotAtEnd(getCauseMessage());
		message = replace(message, "\nException Context:",
				format(": %s%n%n%s", causeMessage, "Exception Context:"));
		return message;
	}

	private String appendDotAtEnd(String message) {
		if (message == null) {
			message = "";
		}
		if (haveDotAtEnd(message)) {
			message = format("%s.", message);
		}
		return message;
	}

	private String getCauseMessage() {
		return getCause().getMessage();
	}

	private boolean haveDotAtEnd(String message) {
		return message.lastIndexOf(".") < message.length() - 1;
	}

}
