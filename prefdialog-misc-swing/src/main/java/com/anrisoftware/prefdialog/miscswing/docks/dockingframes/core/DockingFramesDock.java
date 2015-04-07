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
package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import java.awt.Component;
import java.beans.PropertyChangeListener;
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
import bibliothek.gui.dock.common.SingleCDockable;
import bibliothek.gui.dock.common.event.CControlListener;
import bibliothek.gui.dock.common.event.CFocusListener;
import bibliothek.gui.dock.common.intern.AbstractCDockable;
import bibliothek.gui.dock.common.intern.CDockable;
import bibliothek.gui.dock.common.theme.ThemeMap;
import bibliothek.gui.dock.themes.ThemeManager;
import bibliothek.gui.dock.util.DirectWindowProvider;
import bibliothek.gui.dock.util.NullWindowProvider;
import bibliothek.gui.dock.util.WindowProvider;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.docks.api.Dock;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockFactory;
import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.FocusChangedEvent;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutListener;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutloader.LoadLayoutWorkerFactory;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.layoutsaver.SaveLayoutWorkerFactory;
import com.anrisoftware.prefdialog.miscswing.docks.layouts.dockingframes.DefaultLayoutTask;

/**
 * Docking frames docks.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class DockingFramesDock implements Dock {

    private static final String WORK_AREA_ID = "work";

    private final EventListenerSupport<LayoutListener> layoutListeners;

    private final Map<SingleCDockable, ViewDockWindow> viewDocks;

    private final Map<MultipleCDockable, EditorDockWindow> editorDocks;

    private final EventListenerSupport<ChangeListener> changeListeners;

    private final CFocusListener editorsFocusListener;

    private final CControlListener controlListener;

    @Inject
    private DockingFramesDockLogger log;

    @Inject
    private SaveLayoutWorkerFactory saveFactory;

    @Inject
    private LoadLayoutWorkerFactory loadFactory;

    @Inject
    private DefaultLayoutTask defaultLayoutTask;

    private CControl control;

    private CWorkingArea workingArea;

    private DockingFramesLayoutTask currentLayout;

    /**
     * @see DockFactory#create()
     */
    DockingFramesDock() {
        this.layoutListeners = new EventListenerSupport<LayoutListener>(
                LayoutListener.class);
        this.viewDocks = new ConcurrentHashMap<SingleCDockable, ViewDockWindow>();
        this.editorDocks = new ConcurrentHashMap<MultipleCDockable, EditorDockWindow>();
        this.changeListeners = new EventListenerSupport<ChangeListener>(
                ChangeListener.class);
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
        this.controlListener = new CControlListener() {

            @Override
            public void removed(CControl control, CDockable dockable) {
                editorDocks.remove(dockable);
            }

            @Override
            public void opened(CControl control, CDockable dockable) {
            }

            @Override
            public void closed(CControl control, CDockable dockable) {
            }

            @Override
            public void added(CControl control, CDockable dockable) {
            }
        };
    }

    private void fireEditorDockFocusGained(CDockable dockable) {
        EditorDockWindow editor = editorDocks.get(dockable);
        FocusChangedEvent e = new FocusChangedEvent(editor, true);
        changeListeners.fire().stateChanged(e);
    }

    private void fireEditorDockFocusLost(CDockable dockable) {
        EditorDockWindow editor = editorDocks.get(dockable);
        FocusChangedEvent e = new FocusChangedEvent(editor, false);
        changeListeners.fire().stateChanged(e);
    }

    @OnAwt
    @Override
    public Dock createDock(JFrame frame) {
        WindowProvider provider;
        if (frame == null) {
            provider = new NullWindowProvider();
        } else {
            provider = new DirectWindowProvider(frame);
        }
        this.control = new CControl(provider);
        this.workingArea = control.createWorkingArea(WORK_AREA_ID);
        applyLayout(defaultLayoutTask);
        control.addControlListener(controlListener);
        return this;
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
    @OnAwt
    public void addViewDock(ViewDockWindow dock) {
        SingleCDockable dockable = currentLayout.addView(control, dock);
        dock.setDockable(dockable);
        viewDocks.put(dockable, dock);
    }

    @Override
    @OnAwt
    public void addEditorDock(EditorDockWindow dock) {
        MultipleCDockable dockable = currentLayout.addEditor(workingArea, dock);
        dock.setDockable(dockable);
        editorDocks.put(dockable, dock);
        dockable.addFocusListener(editorsFocusListener);
    }

    /**
     * @throws ClassCastException
     *             if the specified task is not of type
     *             {@link DockingFramesLayoutTask}.
     */
    @Override
    @OnAwt
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
    public synchronized void loadLayout(String name, File file,
            PropertyChangeListener... listeners) throws IOException {
        FileInputStream stream = new FileInputStream(file);
        loadLayout(name, stream, listeners);
        log.layoutLoaded(this, name, file);
    }

    @Override
    public synchronized void loadLayout(String name, InputStream stream,
            PropertyChangeListener... listeners) throws IOException {
        try {
            SwingWorker<InputStream, InputStream> worker = loadFactory.create(
                    layoutListeners, this, name, control, stream);
            for (PropertyChangeListener l : listeners) {
                worker.addPropertyChangeListener(l);
            }
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
