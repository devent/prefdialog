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
package com.anrisoftware.prefdialog.miscswing.cursor;

import static java.awt.Cursor.getPredefinedCursor;
import static javax.swing.SwingUtilities.invokeAndWait;
import static javax.swing.SwingUtilities.isEventDispatchThread;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

/**
 * Sets the cursor on the parent window.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
public class CursorWorker {

    private Component component;

    /**
     * Sets the parent window.
     *
     * @param component
     *            the {@link Window} or a {@link Component} that was added to
     *            the parent window.
     */
    public void setParentWindow(Component component) {
        this.component = component;
    }

    /**
     * Sets the cursor type on the parent window.
     *
     * @see Cursor
     */
    public void setCursor(final int type) {
        final Window parentWindow = getParentWindow();
        if (parentWindow == null) {
            return;
        }
        if (!isEventDispatchThread()) {
            try {
                setCursorAWT(type, parentWindow);
            } catch (InvocationTargetException e) {
            } catch (InterruptedException e) {
            }
        } else {
            setCursor(type, parentWindow);
        }
    }

    private void setCursorAWT(final int type, final Window parentWindow)
            throws InterruptedException, InvocationTargetException {
        invokeAndWait(new Runnable() {

            @Override
            public void run() {
                setCursor(type, parentWindow);
            }

        });
    }

    private Window getParentWindow() {
        if (component instanceof Window) {
            return (Window) component;
        } else {
            return SwingUtilities.getWindowAncestor(component);
        }
    }

    private void setCursor(final int type, final Window parentWindow) {
        parentWindow.setCursor(getPredefinedCursor(type));
    }

}
