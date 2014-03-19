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
package com.anrisoftware.prefdialog.miscswing.docks.api;

import java.awt.Component;

import javax.swing.event.ChangeListener;

/**
 * Child window that is docked.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public interface DockWindow {

    /**
     * Returns the unique identifier of the window. The identifier needs to be
     * unique and constant for all docked windows.
     * 
     * @return the unique identifier of the window.
     */
    String getId();

    /**
     * Returns the title of the window.
     * 
     * @return the title of the window.
     */
    String getTitle();

    /**
     * Returns the AWT component of the window.
     * 
     * @return the {@link Component}
     */
    Component getComponent();

    /**
     * Returns the position of the window relative to the working area.
     * 
     * @return the {@link DockPosition}.
     */
    DockPosition getPosition();

    /**
     * Returns if the user should be able to close the dock.
     * 
     * @return {@code true} if the dock can be closed or {@code false} if not.
     */
    boolean isCloseable();

    /**
     * Returns if the user should be able to move the dock out of the main
     * window.
     * 
     * @return {@code true} if the dock can be moved out or {@code false} if
     *         not.
     */
    boolean isExternalizable();

    /**
     * Returns if the user should be able to maximize the dock.
     * 
     * @return {@code true} if the dock can be maximized or {@code false} if
     *         not.
     */
    boolean isMaximizable();

    /**
     * Returns if the user should be able to minimize the dock.
     * 
     * @return {@code true} if the dock can be minimized or {@code false} if
     *         not.
     */
    boolean isMinimizable();

    /**
     * Returns if the user should be able to stack the dock.
     * 
     * @return {@code true} if the dock can be stacked or {@code false} if not.
     */
    boolean isStackable();

    /**
     * Sets the dockable for this dock window after the dock is added.
     * 
     * @param dockable
     *            the {@link Object} dockable.
     */
    void setDockable(Object dockable);

    /**
     * Returns the dockable for this dock window after the dock is added.
     * 
     * @return the {@link Object} dockable.
     */
    Object getDockable();

    /**
     * Returns that the dock window is visible.
     * 
     * <h1>State Changed Listener</h1>
     * <p>
     * The state change listener is informed of the visible state change.
     * 
     * @return {@code true} if the dock window is visible.
     */
    boolean isVisible();

    /**
     * Returns that the dock window have the focus.
     * 
     * <h1>State Changed Listener</h1>
     * <p>
     * The state change listener is informed of the focus state change.
     * 
     * @return {@code true} if the dock window have the focus.
     */
    boolean isFocus();

    /**
     * Adds the specified state changed listener.
     * 
     * @param listener
     *            the {@link ChangeListener}.
     */
    void addStateChangedListener(ChangeListener listener);

    /**
     * Removes the specified state changed listener.
     * 
     * @param listener
     *            the {@link ChangeListener}.
     */
    void removeStateChangedListener(ChangeListener listener);

}
