package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import bibliothek.util.xml.XElement;

/**
 * Reads and writes objects from and to {@link XElement}.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
@Singleton
class XElementIO {

	private final XElementIOLogger log;

	@Inject
	XElementIO(XElementIOLogger logger) {
		this.log = logger;
	}

	/**
	 * Reads the object from the specified element.
	 * 
	 * @param source
	 *            the source {@link Object} that is reading.
	 * 
	 * @param element
	 *            the {@link XElement}.
	 * 
	 * @return the {@link Object}.
	 * 
	 * @throws RuntimeException
	 *             if there was an error reading the object.
	 */
	@SuppressWarnings("unchecked")
	public <T> T readObject(Object source, XElement element) {
		try {
			byte[] buff = element.getByteArray();
			ByteArrayInputStream out = new ByteArrayInputStream(buff);
			ObjectInputStream stream = new ObjectInputStream(out);
			return (T) stream.readObject();
		} catch (IOException e) {
			throw log.errorReadXML(source, element, e);
		} catch (ClassNotFoundException e) {
			throw log.errorReadXML(source, element, e);
		}
	}

	/**
	 * Writes the object to the specified element.
	 * 
	 * @param source
	 *            the source {@link Object} that is writing.
	 * 
	 * @param element
	 *            the {@link XElement}.
	 * 
	 * @param obj
	 *            the {@link Object} that should be written.
	 * 
	 * @throws RuntimeException
	 *             if there was an error writing the object.
	 */
	public void writeObject(Object source, XElement element, Object obj) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
			ObjectOutputStream stream = new ObjectOutputStream(out);
			stream.writeObject(obj);
			stream.flush();
			element.setByteArray(out.toByteArray());
			out.close();
		} catch (IOException e) {
			throw log.errorWriteXML(source, element, e);
		}
	}

}
