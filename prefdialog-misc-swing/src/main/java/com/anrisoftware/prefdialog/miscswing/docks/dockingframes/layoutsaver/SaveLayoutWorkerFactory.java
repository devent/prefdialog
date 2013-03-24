package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutsaver;

import java.io.OutputStream;

import javax.swing.SwingWorker;

import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;

import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core.DockingFramesDock;

public interface SaveLayoutWorkerFactory {

	SwingWorker<OutputStream, OutputStream> create(
			EventListenerSupport<LayoutListener> listeners,
			DockingFramesDock parent, String name, CControl control,
			OutputStream stream);
}
