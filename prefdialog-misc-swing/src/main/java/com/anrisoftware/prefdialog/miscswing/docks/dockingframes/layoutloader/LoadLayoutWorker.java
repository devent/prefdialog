/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutloader;

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
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesDock;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

/**
 * Loads and restores the layout from a stream in the AWT thread.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class LoadLayoutWorker extends SwingWorker<InputStream, InputStream> {

	private static final int BUFF_SIZE = 20 * 1024;

	private final LoadLayoutWorkerLogger log;

	private final Provider<Kryo> serializer;

	private final EventListenerSupport<LayoutListener> listeners;

	private final DockingFramesDock parent;

	private final Input inputStream;

	private final CControl control;

	private final String name;

	/**
	 * @see LoadLayoutWorkerFactory#create(EventListenerSupport,
	 *      DockingFramesDock, String, CControl, java.io.InputStream)
	 */
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
