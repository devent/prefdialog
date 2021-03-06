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
package com.anrisoftware.prefdialog.miscswing.docks.logdock;

import java.awt.Component;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;
import com.anrisoftware.prefdialog.miscswing.docks.api.DockPosition;
import com.anrisoftware.prefdialog.miscswing.docks.dockingframes.dock.AbstractViewDockWindow;
import com.anrisoftware.prefdialog.miscswing.logpane.CategoryNode;
import com.anrisoftware.prefdialog.miscswing.logpane.LogPane;
import com.anrisoftware.prefdialog.miscswing.logpane.MessageNode;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Log dock. Uses a tree table to display messages ordered in categories.
 *
 * @see LogPane
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
@OnAwt
public class LogDock extends AbstractViewDockWindow {

    private final LogPane pane;

    @Inject
    LogDock(LogPane pane) {
        this.pane = pane;
    }

    @Override
    public String getId() {
        return "problems-dock";
    }

    public void setColumns(List<Object> columnNames) {
        pane.setColumns(columnNames);
    }

    /**
     * Sets the texts resources for the categories and messages.
     *
     * @param texts
     *            the {@link Texts}.
     */
    public void setTexts(Texts texts) {
        pane.setTexts(texts);
    }

    /**
     * Sets the locale for the log dock.
     *
     * @param locale
     *            the {@link Locale}.
     *
     * @since 3.2
     */
    public void setLocale(Locale locale) {
        pane.setLocale(locale);
    }

    public void addCategory(CategoryNode category) {
        pane.addCategory(category);
    }

    /**
     * Adds the message to the list of problems.
     *
     * @param message
     *            the {@link MessageNode}.
     */
    public void addMessage(MessageNode message) {
        pane.addMessage(message);
    }

    @Override
    public Component getComponent() {
        return pane.getComponent();
    }

    @Override
    public DockPosition getPosition() {
        return DockPosition.SOUTH;
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
