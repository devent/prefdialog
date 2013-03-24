package com.anrisoftware.prefdialog.miscswing.docks.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Event of an error in the layout.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@SuppressWarnings("serial")
public class LayoutWorkerErrorEvent extends LayoutWorkerEvent {

	private final Throwable cause;

	/**
	 * @param cause
	 *            the {@link Throwable} cause of the error.
	 * 
	 * @see LayoutWorkerEvent#LayoutWorkerEvent(Object, String)
	 */
	public LayoutWorkerErrorEvent(Object source, String name, Throwable cause) {
		super(source, name);
		this.cause = cause;
	}

	/**
	 * Returns the cause of the error.
	 * 
	 * @return the {@link Throwable} cause.
	 */
	public Throwable getCause() {
		return cause;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).appendSuper(super.toString())
				.append(cause).toString();
	}
}
