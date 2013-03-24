package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.io.InputStream;

import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;

interface LoadLayoutWorkerFactory {

	LoadLayoutWorker create(EventListenerSupport<LayoutListener> listeners,
			DockingFramesDock parent, String name, CControl control,
			InputStream stream);
}
