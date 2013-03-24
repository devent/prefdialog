package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.io.OutputStream;

import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;

interface SaveLayoutWorkerFactory {

	SaveLayoutWorker create(
			EventListenerSupport<LayoutListener> listeners,
			DockingFramesDock parent, String name, CControl control,
			OutputStream stream);
}
