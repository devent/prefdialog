package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutloader;

import java.io.InputStream;

import javax.swing.SwingWorker;

import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesDock;

/**
 * Factory to create the worker to load the layout from a stream.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface LoadLayoutWorkerFactory {

	/**
	 * Creates the worker to load the layout from the specified stream.
	 * 
	 * @param listeners
	 *            {@link EventListenerSupport} with the listeners to inform when
	 *            the layout is loaded.
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
	 *            the {@link InputStream}.
	 * 
	 * @return the {@link SwingWorker}.
	 */
	SwingWorker<InputStream, InputStream> create(
			EventListenerSupport<LayoutListener> listeners,
			DockingFramesDock parent, String name, CControl control,
			InputStream stream);
}
