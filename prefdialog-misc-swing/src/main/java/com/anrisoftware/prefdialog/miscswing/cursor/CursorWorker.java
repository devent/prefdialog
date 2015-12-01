package com.anrisoftware.prefdialog.miscswing.cursor;

import static java.awt.Cursor.getPredefinedCursor;
import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.SwingUtilities.isEventDispatchThread;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Window;

import javax.inject.Singleton;
import javax.swing.SwingUtilities;

/**
 * Sets the cursor on the parent window.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
@Singleton
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
            invokeLater(new Runnable() {

                @Override
                public void run() {
                    setCursor(type, parentWindow);
                }

            });
        } else {
            setCursor(type, parentWindow);
        }
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
