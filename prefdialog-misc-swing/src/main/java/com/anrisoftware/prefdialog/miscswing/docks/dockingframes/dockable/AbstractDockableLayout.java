package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dockable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.inject.Inject;

import bibliothek.gui.dock.common.MultipleCDockableLayout;
import bibliothek.util.xml.XElement;

import com.anrisoftware.prefdialog.miscswing.docks.api.DockWindow;

public abstract class AbstractDockableLayout<WindowType extends DockWindow>
		implements MultipleCDockableLayout {

	private static final String WINDOW_ELEMENT_NAME = "window";

	private WindowType meta;

	private DockableLayoutLogger log;

	private XElementIO elementIo;

	protected AbstractDockableLayout(WindowType window) {
		this.meta = window;
	}

	@Inject
	void setWorkDockableLayoutLogger(DockableLayoutLogger logger) {
		this.log = logger;
	}

	@Inject
	void setXElementIO(XElementIO elementIo) {
		this.elementIo = elementIo;
	}

	/**
	 * Tests if this meta information is set to the specified window.
	 * 
	 * @param window
	 *            the {@link DockWindow}.
	 * 
	 * @return {@code true} if it matches, {@code false} if not.
	 */
	public boolean match(WindowType window) {
		return window.match(window);
	}

	/**
	 * Returns the meta information.
	 * 
	 * @return the {@link DockWindow}.
	 */
	public WindowType getMeta() {
		return meta;
	}

	@Override
	public void writeStream(DataOutputStream out) throws IOException {
		ObjectOutputStream obj = new ObjectOutputStream(out);
		meta.writeExternal(obj);
		out.flush();
	}

	@Override
	public void readStream(DataInputStream in) throws IOException {
		try {
			ObjectInputStream obj = new ObjectInputStream(in);
			meta.readExternal(obj);
		} catch (ClassNotFoundException e) {
			throw log.errorReadStream(this, e);
		}
	}

	@Override
	public void writeXML(XElement element) {
		XElement windowElement = new XElement(WINDOW_ELEMENT_NAME);
		elementIo.writeObject(this, windowElement, meta);
		element.addElement(windowElement);
	}

	@Override
	public void readXML(XElement element) {
		XElement windowElement = element.getElement(WINDOW_ELEMENT_NAME);
		meta = elementIo.readObject(this, windowElement);
	}

}