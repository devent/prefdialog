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
