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
package com.anrisoftware.prefdialog.miscswing.docks.dockingframes;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

import bibliothek.gui.dock.common.MultipleCDockable;

import com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition;
import com.anrisoftware.prefdialog.miscswing.docks.api.EditorDockWindow;

/**
 * Color editor dock.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class ColorEditorDock implements EditorDockWindow {

    private String id;

    private String title;

    private DockPosition position;

    private Color color;

    private MultipleCDockable dockable;

    public ColorEditorDock() {
    }

    public ColorEditorDock(String id, String title, DockPosition position,
            Color color) {
        this.id = id;
        this.title = title;
        this.position = position;
        this.color = color;
    }

    @Override
    public void setDockable(Object dockable) {
        this.dockable = (MultipleCDockable) dockable;
    }

    @Override
    public Object getDockable() {
        return dockable;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Component getComponent() {
        JPanel panel = new JPanel();
        panel.setBackground(color);
        return panel;
    }

    @Override
    public DockPosition getPosition() {
        return position;
    }

    @Override
    public boolean isCloseable() {
        return true;
    }

    @Override
    public boolean isExternalizable() {
        return true;
    }

    @Override
    public boolean isMaximizable() {
        return true;
    }

    @Override
    public boolean isMinimizable() {
        return true;
    }

    @Override
    public boolean isStackable() {
        return true;
    }

}
