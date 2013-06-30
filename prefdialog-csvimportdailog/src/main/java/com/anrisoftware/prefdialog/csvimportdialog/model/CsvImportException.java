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
