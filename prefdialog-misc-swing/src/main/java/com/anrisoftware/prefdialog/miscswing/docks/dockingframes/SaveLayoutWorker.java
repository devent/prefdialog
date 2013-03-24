package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutWorkerErrorEvent;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutWorkerEvent;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

class SaveLayoutWorker extends SwingWorker<OutputStream, OutputStream> {

	private static final int BUFF_SIZE = 20 * 1024;

	private final SaveLayoutWorkerLogger log;

	private final EventListenerSupport<LayoutListener> listeners;

	private final DockingFramesDock parent;

	private final BufferedOutputStream outputStream;

	private final CControl control;

	private final String name;

	private final Provider<Kryo> serializer;

	@Inject
	SaveLayoutWorker(SaveLayoutWorkerLogger logger, Provider<Kryo> serializer,
			@Assisted EventListenerSupport<LayoutListener> listeners,
			@Assisted DockingFramesDock parent, @Assisted String name,
			@Assisted CControl control, @Assisted OutputStream stream) {
		this.log = logger;
		this.serializer = serializer;
		this.listeners = listeners;
		this.parent = parent;
		this.name = name;
		this.control = control;
		this.outputStream = new BufferedOutputStream(stream, BUFF_SIZE);
	}

	@Override
	protected OutputStream doInBackground() throws Exception {
		saveCurrentLayout();
		saveDocksLayout();
		outputStream.close();
		return outputStream;
	}

	private void saveCurrentLayout() {
		LayoutTask currentLayout = parent.getCurrentLayout();
		Output output = new Output(outputStream);
		Kryo serializer = this.serializer.get();
		serializer.writeClassAndObject(output, currentLayout);
		output.flush();
	}

	private void saveDocksLayout() throws InterruptedException {
		publish(outputStream);
		synchronized (this) {
			wait();
		}
	}

	@Override
	protected void process(List<OutputStream> chunks) {
		try {
			control.save(name);
			control.write(new DataOutputStream(outputStream));
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
