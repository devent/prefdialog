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
package com.anrisoftware.prefdialog.miscswing.actions;

import static javax.swing.SwingUtilities.invokeLater;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Future;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorCategory;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.ErrorNode;
import com.anrisoftware.prefdialog.miscswing.logwindowdock.LogWindowDock;
import com.anrisoftware.prefdialog.miscswing.statusbar.StatusBar;

/**
 * Retrieves the value from the future task.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public abstract class AbstractAppAction implements PropertyChangeListener {

    @Inject
    private AbstractAppActionLogger log;

    private StatusBar statusBar;

    private LogWindowDock logDock;

    public void setLogWindowDock(LogWindowDock dock) {
        this.logDock = dock;
    }

    @Override
    public final void propertyChange(PropertyChangeEvent evt) {
        try {
            doPropertyChange(evt);
        } catch (Throwable e) {
            addErrorMessage(e);
            e.printStackTrace();
        }
    }

    /**
     * Adds the exception to the errors window.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called <i>outside</i> the AWT thread.
     *
     * @param e
     *            the {@link Throwable} exception.
     */
    protected void addErrorMessage(final Throwable e) {
        invokeLater(new Runnable() {

            @Override
            public void run() {
                ErrorCategory category = logDock.getErrorCategory();
                ErrorNode node = log.errorAction(category, e);
                logDock.addErrorMessage(node);
            }
        });
    }

    /**
     * Do the application action.
     *
     * @see PropertyChangeListener#propertyChange(PropertyChangeEvent)
     */
    protected abstract void doPropertyChange(PropertyChangeEvent evt)
            throws Exception;

    /**
     * Returns the future task from the property change event.
     *
     * @param evt
     *            the {@link PropertyChangeEvent} that have the source set to
     *            the future task.
     *
     * @return the {@link Future} task.
     */
    @SuppressWarnings("unchecked")
    protected final <T> Future<T> asFuture(PropertyChangeEvent evt) {
        return (Future<T>) evt.getSource();
    }

    /**
     * Returns the value from the future task and logs any exceptions.
     *
     * @param future
     *            the {@link Future} task.
     *
     * @return the value.
     */
    protected final <T> T fromFuture(Future<T> future) {
        return log.fromFuture(future);
    }

    /**
     * Sets the status bar of the main window.
     *
     * @param bar
     *            the {@link StatusBar}.
     */
    public void setStatusBar(StatusBar bar) {
        this.statusBar = bar;
    }

    /**
     * Returns the status bar of the main window.
     *
     * @return the {@link StatusBar}.
     */
    public StatusBar getStatusBar() {
        return statusBar;
    }

    /**
     * @see StatusBar#setMessage(boolean, Object, Object...)
     */
    public void setStatusBarMessage(boolean busy, Enum<?> message,
            Object... args) {
        StatusBar statusBar = getStatusBar();
        statusBar.setMessage(busy, message, args);
    }

    /**
     * @see StatusBar#setProgress(int, int, Object, Object...)
     */
    public void setStatusBarProgress(int max, int done, Enum<?> message,
            Object... args) {
        StatusBar statusBar = getStatusBar();
        statusBar.setProgress(max, done, message, args);
    }

}
