/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.event.EventListenerSupport;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.MultipleCDockable;
import bibliothek.gui.dock.common.event.CDockableLocationEvent;
import bibliothek.gui.dock.common.event.CDockableLocationListener;
import bibliothek.gui.dock.common.event.CFocusListener;
import bibliothek.gui.dock.common.intern.AbstractCDockable;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.theme.ThemeMap;
import bibliothek.gui.dock.themes.ThemeManager;
import bibliothek.gui.dock.util.DirectWindowProvider;
import bibliothek.gui.dock.util.NullWindowProvider;
import bibliothek.gui.dock.util.WindowProvider;

import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory;
import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.FocusChangedEvent;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.ShowingChangedEvent;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutloader.LoadLayoutWorkerFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutsaver.SaveLayoutWorkerFactory;
import com.anrisoftware.prefdialog.miscswing.docks.layouts.dockingframes.DefaultLayoutTask;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * Docking frames docks.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class DockingFramesDock implements Dock {

	private static final String WORK_AREA_ID = "work";

	private final DockingFramesDockLogger log;

	private final SaveLayoutWorkerFactory saveFactory;

	private final EventListenerSupport<LayoutListener> layoutListeners;

	private final Map<String, ViewDockWindow> viewDocks;

	private final Map<MultipleCDockable, EditorDockWindow> editorDocks;

	private final LoadLayoutWorkerFactory loadFactory;

	private final EventListenerSupport<ChangeListener> changeListeners;

	private final CControl control;

	private final CWorkingArea workingArea;

	private DockingFramesLayoutTask currentLayout;

	private final CDockableLocationListener editorsLocationListener;

	private final CFocusListener editorsFocusListener;

	/**
	 * @see DockFactory#create()
	 */
	@AssistedInject
	DockingFramesDock(DockingFramesDockLogger logger,
			SaveLayoutWorkerFactory saveFactory,
			LoadLayoutWorkerFactory loadFactory) {
		this(logger, saveFactory, loadFactory, new NullWindowProvider());
	}

	/**
	 * @see DockFactory#create(JFrame)
	 */
	@AssistedInject
	DockingFramesDock(DockingFramesDockLogger logger,
			SaveLayoutWorkerFactory saveFactory,
			LoadLayoutWorkerFactory loadFactory, @Assisted JFrame frame) {
		this(logger, saveFactory, loadFactory, new DirectWindowProvider(frame));
	}

	private DockingFramesDock(DockingFramesDockLogger logger,
			SaveLayoutWorkerFactory saveFactory,
			LoadLayoutWorkerFactory loadFactory, WindowProvider window) {
		this.log = logger;
		this.control = new CControl(window);
		this.workingArea = control.createWorkingArea(WORK_AREA_ID);
		this.saveFactory = saveFactory;
		this.loadFactory = loadFactory;
		this.layoutListeners = new EventListenerSupport<LayoutListener>(
				LayoutListener.class);
		this.viewDocks = new ConcurrentHashMap<String, ViewDockWindow>();
		this.editorDocks = new ConcurrentHashMap<MultipleCDockable, EditorDockWindow>();
		this.changeListeners = new EventListenerSupport<ChangeListener>(
				ChangeListener.class);
		this.editorsLocationListener = new CDockableLocationListener() {

			@Override
			public void changed(CDockableLocationEvent event) {
				if (event.isShowingChanged()) {
					fireEditorDockShowingChanged(event);
				}
			}
		};
		this.editorsFocusListener = new CFocusListener() {

			@Override
			public void focusLost(CDockable dockable) {
				fireEditorDockFocusLost(dockable);
			}

			@Override
			public void focusGained(CDockable dockable) {
				fireEditorDockFocusGained(dockable);
			}
		};
	}

	private void fireEditorDockFocusGained(CDockable dockable) {
		EditorDockWindow editor = editorDocks.get(dockable);
		changeListeners.fire()
				.stateChanged(new FocusChangedEvent(editor, true));
	}

	private void fireEditorDockFocusLost(CDockable dockable) {
		EditorDockWindow editor = editorDocks.get(dockable);
		changeListeners.fire().stateChanged(
				new FocusChangedEvent(editor, false));
	}

	private void fireEditorDockShowingChanged(CDockableLocationEvent event) {
		EditorDockWindow editor = editorDocks.get(event.getDockable());
		changeListeners.fire().stateChanged(
				new ShowingChangedEvent(editor, event.getNewShowing()));
	}

	@Inject
	public void setDefaultLayout(DefaultLayoutTask layout) {
		this.currentLayout = layout;
		currentLayout.setupLayout(control, workingArea, viewDocks);
	}

	/**
	 * Returns the theme manager so the user interface of the dock can be
	 * modified.
	 * 
	 * @return the {@link ThemeManager}.
	 */
	public ThemeManager getThemeManager() {
		return control.getController().getThemeManager();
	}

	@Override
	public Component getAWTComponent() {
		return control.getContentArea();
	}

	@Override
	public void addViewDock(ViewDockWindow dock) {
		viewDocks.put(dock.getId(), dock);
		currentLayout.addView(control, dock);
	}

	@Override
	public void addEditorDock(EditorDockWindow dock) {
		MultipleCDockable dockable = currentLayout.addEditor(workingArea, dock);
		editorDocks.put(dockable, dock);
		dockable.addCDockableLocationListener(editorsLocationListener);
		dockable.addFocusListener(editorsFocusListener);
	}

	/**
	 * @throws ClassCastException
	 *             if the specified task is not of type
	 *             {@link DockingFramesLayoutTask}.
	 */
	@Override
	public synchronized void applyLayout(LayoutTask layout) {
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
					.create(layoutListeners, this, name, control, stream);
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
					layoutListeners, this, name, control, stream);
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

	@Override
	public void requestFocus(EditorDockWindow dock) {
		MultipleCDockable dockable = findDockable(dock);
		if (dockable == null) {
			return;
		}
		if (dockable instanceof AbstractCDockable) {
			((AbstractCDockable) dockable).toFront();
		}
	}

	private MultipleCDockable findDockable(EditorDockWindow dock) {
		for (Map.Entry<MultipleCDockable, EditorDockWindow> entry : editorDocks
				.entrySet()) {
			if (entry.getValue().getId().equals(dock.getId())) {
				return entry.getKey();
			}
		}
		return null;
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
		ThemeMap themes = control.getThemes();
		themes.select(name);
	}

	@Override
	public void addLayoutListener(LayoutListener listener) {
		layoutListeners.addListener(listener);
	}

	@Override
	public void removeLayoutListener(LayoutListener listener) {
		layoutListeners.removeListener(listener);
	}

	@Override
	public void addStateChangedListener(ChangeListener listener) {
		changeListeners.addListener(listener);
	}

	@Override
	public void removeStateChangedListener(ChangeListener listener) {
		changeListeners.removeListener(listener);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
