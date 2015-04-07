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
package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutsaver;

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
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesDock;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

/**
 * Stores and saves the layout in a stream in the AWT thread.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class SaveLayoutWorker extends SwingWorker<OutputStream, OutputStream> {

	private static final int BUFF_SIZE = 20 * 1024;

	private final SaveLayoutWorkerLogger log;

	private final EventListenerSupport<LayoutListener> listeners;

	private final DockingFramesDock parent;

	private final BufferedOutputStream outputStream;

	private final CControl control;

	private final String name;

	private final Provider<Kryo> serializer;

	/**
	 * @see SaveLayoutWorkerFactory#create(EventListenerSupport,
	 *      DockingFramesDock, String, CControl, OutputStream)
	 */
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
