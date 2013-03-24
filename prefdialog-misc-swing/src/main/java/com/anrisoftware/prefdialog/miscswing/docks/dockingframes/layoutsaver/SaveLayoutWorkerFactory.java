package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutsaver;

import java.io.OutputStream;

import javax.swing.SwingWorker;

import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesDock;

/**
 * Factory to create the worker to save the layout in a stream.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface SaveLayoutWorkerFactory {

	/**
	 * Creates the worker to save the layout in the specified stream.
	 * 
	 * @param listeners
	 *            {@link EventListenerSupport} with the listeners to inform when
	 *            the layout is saved.
	 * 
	 * @param parent
	 *            the parent {@link DockingFramesDock}.
	 * 
	 * @param name
	 *            the name of the layout.
	 * 
	 * @param control
	 *            the {@link CControl}.
	 * 
	 * @param stream
	 *            the {@link OutputStream}.
	 * 
	 * @return the {@link SwingWorker}.
	 */
	SwingWorker<OutputStream, OutputStream> create(
			EventListenerSupport<LayoutListener> listeners,
			DockingFramesDock parent, String name, CControl control,
			OutputStream stream);
}
