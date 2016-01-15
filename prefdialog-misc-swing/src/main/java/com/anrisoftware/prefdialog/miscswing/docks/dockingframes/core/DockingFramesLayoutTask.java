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
package com.anrisoftware.prefdialog.miscswing.docks.dockingframes.core;

import java.io.Serializable;
import java.util.Map;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CWorkingArea;
import bibliothek.gui.dock.common.MultipleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;
import com.anrisoftware.prefdialog.miscswing.docks.api.LayoutTask;
import com.anrisoftware.prefdialog.miscswing.docks.api.ViewDockWindow;

/**
 * Apply a layout for the Docking Frames.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface DockingFramesLayoutTask extends LayoutTask, Serializable {

    /**
     * Sets the layout.
     * 
     * <h2>AWT Threading</h2>
     * <p>
     * Should be done in the AWT thread.
     * 
     * @param control
     *            the {@link CControl}.
     * 
     * @param workingArea
     *            the {@link CWorkingArea}.
     * 
     * @param docks
     *            the {@link Map} of the docks that are outside of the work
     *            area.
     */
    void setupLayout(CControl control, CWorkingArea workingArea,
            Map<SingleCDockable, ViewDockWindow> docks);

    /**
     * Adds a new editor dock window.
     * 
     * <h2>AWT Threading</h2>
     * <p>
     * Should be done in the AWT thread.
     * 
     * @param control
     *            the {@link CControl} where to add the dock.
     * 
     * @param dock
     *            the {@link EditorDockWindow} dock.
     * 
     * @return the {@link MultipleCDockable} of the editor dock.
     */
    MultipleCDockable addEditor(CWorkingArea workingArea, EditorDockWindow dock);

    /**
     * Adds a new view dock window.
     * 
     * <h2>AWT Threading</h2>
     * <p>
     * Should be done in the AWT thread.
     * 
     * @param control
     *            the {@link CControl} where to add the dock.
     * 
     * @param dock
     *            the {@link ViewDockWindow} dock.
     * 
     * @return the {@link SingleCDockable} of the view dock.
     */
    SingleCDockable addView(CControl control, ViewDockWindow dock);
}
