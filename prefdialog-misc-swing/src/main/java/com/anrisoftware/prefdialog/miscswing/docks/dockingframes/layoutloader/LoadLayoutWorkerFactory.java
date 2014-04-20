/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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
