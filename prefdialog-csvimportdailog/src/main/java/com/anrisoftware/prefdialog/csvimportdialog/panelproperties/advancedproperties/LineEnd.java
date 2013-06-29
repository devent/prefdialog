package com.anrisoftware.prefdialog.csvimportdialog.panelproperties.advancedproperties;

/**
 * Possible line end symbols.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public enum LineEnd {

	/**
	 * Unix like symbols.
	 */
	UNIX("\n"),

	/**
	 * Windows like symbols.
	 */
	WINDOWS("\r\n"),

	/**
	 * System default symbols.
	 */
	DEFAULT(null);

	private String symbols;

	private LineEnd(String symbols) {
		this.symbols = symbols;
	}

	/**
	 * Returns the line end symbols.
	 * 
	 * @return the symbols {@link String} or {@code null} if the system default
	 *         line end symbols should be used.
	 */
	public String getSymbols() {
		return symbols;
	}
}
