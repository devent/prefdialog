package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutWorkerErrorEvent;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutWorkerEvent;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

class LoadLayoutWorker extends SwingWorker<InputStream, InputStream> {

	private static final int BUFF_SIZE = 20 * 1024;

	private final LoadLayoutWorkerLogger log;

	private final Provider<Kryo> serializer;

	private final EventListenerSupport<LayoutListener> listeners;

	private final DockingFramesDock parent;

	private final Input inputStream;

	private final CControl control;

	private final String name;

	@Inject
	LoadLayoutWorker(LoadLayoutWorkerLogger logger, Provider<Kryo> serializer,
			@Assisted EventListenerSupport<LayoutListener> listeners,
			@Assisted DockingFramesDock parent, @Assisted String name,
			@Assisted CControl control, @Assisted InputStream stream) {
		this.log = logger;
		this.serializer = serializer;
		this.listeners = listeners;
		this.parent = parent;
		this.name = name;
		this.control = control;
		this.inputStream = new Input(stream, BUFF_SIZE);
	}

	@Override
	protected InputStream doInBackground() throws Exception {
		loadCurrentLayout();
		loadDocksLayout();
		inputStream.close();
		return inputStream;
	}

	private void loadCurrentLayout() {
		Kryo serializer = this.serializer.get();
		LayoutTask layout = (LayoutTask) serializer
				.readClassAndObject(inputStream);
		parent.setCurrentLayout(layout);
	}

	private void loadDocksLayout() throws InterruptedException {
		publish(inputStream);
		synchronized (this) {
			wait();
		}
	}

	@Override
	protected void process(List<InputStream> chunks) {
		try {
			control.read(new DataInputStream(inputStream));
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
