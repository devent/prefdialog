package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutWorkerErrorEvent;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutWorkerEvent;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class SaveLayoutWorker extends SwingWorker<OutputStream, OutputStream> {

	private final SaveLayoutWorkerLogger log;

	private final EventListenerSupport<LayoutListener> listeners;

	private final DockingFramesDock parent;

	private final OutputStream outputStream;

	private final CControl control;

	private final String name;

	private DataOutputStream dataStream;

	private ObjectOutputStream objectStream;

	@Inject
	SaveLayoutWorker(SaveLayoutWorkerLogger logger,
			@Assisted EventListenerSupport<LayoutListener> listeners,
			@Assisted DockingFramesDock parent, @Assisted String name,
			@Assisted CControl control, @Assisted OutputStream stream) {
		this.log = logger;
		this.listeners = listeners;
		this.parent = parent;
		this.name = name;
		this.control = control;
		this.outputStream = stream;
	}

	@Override
	protected OutputStream doInBackground() throws Exception {
		this.dataStream = new DataOutputStream(outputStream);
		this.objectStream = new ObjectOutputStream(dataStream);
		publish(outputStream);
		synchronized (this) {
			wait();
		}
		outputStream.close();
		return outputStream;
	}

	@Override
	protected void process(List<OutputStream> chunks) {
		try {
			control.save(name);
			control.write(dataStream);
			fireLayoutSaved();
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

	private void fireLayoutSaved() {
		log.layoutLoaded(parent, name);
		listeners.fire().layoutSaved(new LayoutWorkerEvent(parent, name));
	}
}
