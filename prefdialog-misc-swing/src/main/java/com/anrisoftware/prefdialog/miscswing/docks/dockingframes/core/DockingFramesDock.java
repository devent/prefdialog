package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.theme.ThemeMap;

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutloader.LoadLayoutWorkerFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutsaver.SaveLayoutWorkerFactory;

public class DockingFramesDock implements Dock {

	private static final String WORK_AREA_ID = "work";

	private final DockingFramesDockLogger log;

	private final SaveLayoutWorkerFactory saveFactory;

	private final EventListenerSupport<LayoutListener> listeners;

	private final Map<String, ViewDockWindow> viewDocks;

	private final List<EditorDockWindow> editorDocks;

	private final LoadLayoutWorkerFactory loadFactory;

	private CControl control;

	private CWorkingArea workingArea;

	private DockingFramesLayoutTask currentLayout;

	@Inject
	DockingFramesDock(DockingFramesDockLogger logger,
			SaveLayoutWorkerFactory saveFactory,
			LoadLayoutWorkerFactory loadFactory) {
		this.log = logger;
		this.saveFactory = saveFactory;
		this.loadFactory = loadFactory;
		this.listeners = new EventListenerSupport<LayoutListener>(
				LayoutListener.class);
		this.viewDocks = new ConcurrentHashMap<String, ViewDockWindow>();
		this.editorDocks = new CopyOnWriteArrayList<EditorDockWindow>();
	}

	@Override
	public Dock withFrame(JFrame frame) {
		control = new CControl(frame);
		frame.add(getComponent(), BorderLayout.CENTER);
		workingArea = control.createWorkingArea(WORK_AREA_ID);
		return this;
	}

	@Override
	public Component getComponent() {
		return control.getContentArea();
	}

	@Override
	public void addViewDock(ViewDockWindow dock) {
		viewDocks.put(dock.getId(), dock);
		currentLayout.addView(control, dock);
	}

	@Override
	public void addEditorDock(EditorDockWindow dock) {
		editorDocks.add(dock);
		currentLayout.addEditor(workingArea, dock);
	}

	/**
	 * @throws ClassCastException
	 *             if the specified task is not of type
	 *             {@link DockingFramesLayoutTask}.
	 */
	@Override
	public void applyLayout(LayoutTask layout) {
		this.currentLayout = (DockingFramesLayoutTask) layout;
		currentLayout.setupLayout(control, workingArea, viewDocks);
	}

	@Override
	public synchronized void saveLayout(String name, File file)
			throws IOException {
		FileOutputStream stream = new FileOutputStream(file);
		saveLayout(name, stream);
		log.layoutSaved(this, name, file);
	}

	@Override
	public synchronized void saveLayout(String name, OutputStream stream)
			throws IOException {
		try {
			SwingWorker<OutputStream, OutputStream> worker = saveFactory
					.create(listeners, this, name, control, stream);
			worker.execute();
			worker.get();
		} catch (InterruptedException e) {
			throw log.saveLayoutInterrupted(this, name);
		} catch (ExecutionException e) {
			throw log.saveLayoutError(this, name, e.getCause());
		}
	}

	@Override
	public synchronized void loadLayout(String name, File file)
			throws IOException {
		FileInputStream stream = new FileInputStream(file);
		loadLayout(name, stream);
		log.layoutLoaded(this, name, file);
	}

	@Override
	public synchronized void loadLayout(String name, InputStream stream)
			throws IOException {
		try {
			SwingWorker<InputStream, InputStream> worker = loadFactory.create(
					listeners, this, name, control, stream);
			worker.execute();
			worker.get();
		} catch (InterruptedException e) {
			throw log.loadLayoutInterrupted(this, name);
		} catch (ExecutionException e) {
			throw log.loadLayoutError(this, name, e.getCause());
		}
	}

	@Override
	public LayoutTask getCurrentLayout() {
		return currentLayout;
	}

	/**
	 * Sets the current layout without applying it.
	 * 
	 * @param currentLayout
	 *            the new current layout.
	 * 
	 * @throws ClassCastException
	 *             if the specified task is not of type
	 *             {@link DockingFramesLayoutTask}.
	 */
	public void setCurrentLayout(LayoutTask currentLayout) {
		this.currentLayout = (DockingFramesLayoutTask) currentLayout;
	}

	@Override
	public void setTheme(final String name) {
		invokeLater(new Runnable() {

			@Override
			public void run() {
				ThemeMap themes = control.getThemes();
				themes.select(name);
			}
		});
	}

	@Override
	public void addLayoutListener(LayoutListener listener) {
		listeners.addListener(listener);
	}

	@Override
	public void removeLayoutListener(LayoutListener listener) {
		listeners.removeListener(listener);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
