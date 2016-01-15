/*
 * Copyright 2013-2016 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.docks.api;

import java.awt.Component;
import java.awt.Window;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.event.ChangeListener;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Layouts the child windows inside and outside the working area.
 * <p>
 * Example:
 *
 * <pre>
 * dock = dock.createDock(frame);
 * frame.add(dock.getAWTComponent(), BorderLayout.CENTER);
 * dock.applyLayout(defaultLayout);
 * dock.addViewDock(viewDock);
 * dock.addEditorDock(editor);
 * dock.addEditorDock(editor);
 * </pre>
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface Dock {

    /**
     * Creates the dock with the specified frame.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param parentWindow
     *            the {@link Window} or {@code null}.
     *
     * @return this {@link Dock}.
     */
    @OnAwt
    Dock createDock(Window parentWindow);

    /**
     * Returns the AWT component of the dock to be added in a container.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link Component}.
     */
    @OnAwt
    Component getAWTComponent();

    /**
     * Adds a dock in the outside of the working area.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param dock
     *            the {@link ViewDockWindow}.
     */
    @OnAwt
    void addViewDock(ViewDockWindow dock);

    /**
     * Adds a dock in the working area.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param dock
     *            the {@link EditorDockWindow}.
     */
    @OnAwt
    void addEditorDock(EditorDockWindow dock);

    /**
     * Apply the layout.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param layout
     *            the {@link LayoutTask} that applies the layout.
     */
    @OnAwt
    void applyLayout(LayoutTask layout);

    /**
     * Saves the current layout under the specified name.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called <i>outside</i> the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the name of the perspective.
     *
     * @param file
     *            the {@link File} file where to save the layout.
     *
     * @param listeners
     *            optionally, {@link PropertyChangeListener} listeners that are
     *            informed when the layout have been loaded and set.
     *
     * @throws LayoutException
     *             if there was an error saving the layout.
     *
     * @since 3.5
     */
    void saveLayout(String name, File file, PropertyChangeListener... listeners)
            throws LayoutException;

    /**
     * Saves the current layout under the specified name.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called <i>outside</i> the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the name of the layout.
     *
     * @param stream
     *            the {@link OutputStream} stream where to save the layout.
     *
     * @param listeners
     *            optionally, {@link PropertyChangeListener} listeners that are
     *            informed when the layout have been loaded and set.
     *
     * @throws LayoutException
     *             if there was an error saving the layout.
     *
     * @since 3.5
     */
    void saveLayout(String name, OutputStream stream,
            PropertyChangeListener... listeners) throws LayoutException;

    /**
     * Loads the previously saved layout with the specified name.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called <i>outside</i> the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the name of the layout.
     *
     * @param file
     *            the {@link File} file from where to load the layout.
     *
     * @param listeners
     *            optionally, {@link PropertyChangeListener} listeners that are
     *            informed when the layout have been loaded and set.
     *
     * @throws LayoutException
     *             if there was an error loading the layout.
     *
     * @since 3.5
     */
    void loadLayout(String name, File file, PropertyChangeListener... listeners)
            throws LayoutException;

    /**
     * Loads the previously saved layout with the specified name.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called <i>outside</i> the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the name of the layout.
     *
     * @param stream
     *            the {@link InputStream} stream from where to load the layout.
     *
     * @param listeners
     *            optionally, {@link PropertyChangeListener} listeners that are
     *            informed when the layout have been loaded and set.
     *
     * @throws LayoutException
     *             if there was an error loading the layout.
     *
     * @since 3.5
     */
    void loadLayout(String name, InputStream stream,
            PropertyChangeListener... listeners) throws LayoutException;

    /**
     * Returns the current active layout.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @return the {@link LayoutTask}.
     */
    @OnAwt
    LayoutTask getCurrentLayout();

    /**
     * Sets a theme.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called in the AWT event dispatch thread.
     * </p>
     *
     * @param name
     *            the theme name.
     */
    @OnAwt
    void setTheme(String name);

    /**
     * Adds a new listener that is informed of layout changes.
     *
     * @param listener
     *            the {@link LayoutListener}.
     */
    void addLayoutListener(LayoutListener listener);

    /**
     * Focus the specified editor dock.
     *
     * @param dock
     *            the {@link EditorDockWindow}.
     */
    void requestFocus(EditorDockWindow dock);

    /**
     * Removes the listener that was informed of layout changes.
     *
     * @param listener
     *            the {@link LayoutListener}.
     */
    void removeLayoutListener(LayoutListener listener);

    /**
     * Adds a new listener that is informed of changes of the docks.
     *
     * @param listener
     *            the {@link ChangeListener}.
     */
    void addStateChangedListener(ChangeListener listener);

    /**
     * Removes the listener that was informed of changes of the docks.
     *
     * @param listener
     *            the {@link ChangeListener}.
     */
    void removeStateChangedListener(ChangeListener listener);

}
