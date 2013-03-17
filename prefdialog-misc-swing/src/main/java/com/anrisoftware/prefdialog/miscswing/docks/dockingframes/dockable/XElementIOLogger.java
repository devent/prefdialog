package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable;

import static java.lang.String.format;

import java.io.IOException;

import bibliothek.util.xml.XElement;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging messages for {@link XElementIO}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class XElementIOLogger extends AbstractLogger {

	/**
	 * Create logger for {@link XElementIO}.
	 */
	public XElementIOLogger() {
		super(XElementIO.class);
	}

	RuntimeException errorWriteXML(Object source, XElement element,
			IOException e) {
		String message = format("Error write XML to element %s in %s", element,
				source);
		RuntimeException ex = new RuntimeException(message, e);
		logException(message, ex);
		return ex;
	}

	RuntimeException errorReadXML(Object source, XElement element, Exception e) {
		String message = format("Error read XML from element %s in %s",
				element, source);
		RuntimeException ex = new RuntimeException(message, e);
		logException(message, ex);
		return ex;
	}
}
