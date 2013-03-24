package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutWorkerErrorEvent;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutWorkerEvent;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class LoadLayoutWorker extends SwingWorker<InputStream, InputStream> {

	private final LoadLayoutWorkerLogger log;

	private final EventListenerSupport<LayoutListener> listeners;

	private final DockingFramesDock parent;

	private final InputStream inputStream;

	private final CControl control;

	private final String name;

	private DataInputStream dataStream;

	private ObjectInputStream objectStream;

	@Inject
	LoadLayoutWorker(LoadLayoutWorkerLogger logger,
			@Assisted EventListenerSupport<LayoutListener> listeners,
			@Assisted DockingFramesDock parent, @Assisted String name,
			@Assisted CControl control, @Assisted InputStream stream) {
		this.log = logger;
		this.listeners = listeners;
		this.parent = parent;
		this.name = name;
		this.control = control;
		this.inputStream = stream;
	}

	@Override
	protected InputStream doInBackground() throws Exception {
		this.dataStream = new DataInputStream(inputStream);
		this.objectStream = new ObjectInputStream(dataStream);
		publish(inputStream);
		synchronized (this) {
			wait();
		}
		inputStream.close();
		return inputStream;
	}

	@Override
	protected void process(List<InputStream> chunks) {
		try {
			control.read(dataStream);
			control.load(name);
			fireLayoutLoaded();
		} catch (IOException e) {
			fireLayoutError(e);
		}
		synchronized (this) {
			notify();
		}
	}

	private void fireLayoutError(IOException e) {
		log.loadLayoutError(parent, name, e);
		listeners.fire().layoutError(
				new LayoutWorkerErrorEvent(parent, name, e));
	}

	private void fireLayoutLoaded() {
		log.layoutLoaded(parent, name);
		listeners.fire().layoutLoaded(new LayoutWorkerEvent(parent, name));
	}
}
