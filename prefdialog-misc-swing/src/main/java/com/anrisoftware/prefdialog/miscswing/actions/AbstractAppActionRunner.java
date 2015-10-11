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

import static com.anrisoftware.prefdialog.miscswing.actions.AppActionListenerResource.application_error_description;
import static org.apache.commons.lang3.Validate.notNull;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.logwindowdock.LogWindowDock;
import com.anrisoftware.prefdialog.miscswing.statusbar.StatusBar;

/**
 * Runs the application action and catches and logs unexpected errors.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.3
 */
public abstract class AbstractAppActionRunner implements Runnable {

    @Inject
    private AbstractAppActionRunnerLogger log;

    /**
     * Sets the log window dock.
     *
     * @param dock
     *            the {@link LogWindowDock}.
     */
    public void setLogWindowDock(LogWindowDock dock) {
        log.setLogWindowDock(dock);
    }

    /**
     * Returns the log window dock.
     *
     * @return the {@link LogWindowDock}.
     */
    public LogWindowDock getLogWindowDock() {
        return log.getLogWindowDock();
    }

    /**
     * Sets the status bar.
     *
     * @param statusBar
     *            the {@link StatusBar}.
     */
    public void setStatusBar(StatusBar statusBar) {
        log.setStatusBar(statusBar);
    }

    /**
     * Returns the status bar.
     *
     * @return the {@link StatusBar}.
     */
    public StatusBar getStatusBar() {
        return log.getStatusBar();
    }

    @Override
    public final void run() {
        notNull(getStatusBar(), "statusBar");
        notNull(getLogWindowDock(), "logWindowDock");
        try {
            doRun();
        } catch (Throwable e) {
            log.logException(e, application_error_description,
                    CAUSE_MESSAGE_ARG, e.getLocalizedMessage());
        }
    }

    /**
     * Do the application action.
     */
    protected abstract void doRun() throws Exception;

    /**
     * Adds the exception to the errors window.
     *
     * @param ex
     *            the {@link Throwable} exception.
     *
     * @param message
     *            the {@link Object} messages.
     *
     * @param args
     *            the {@link Object} arguments.
     */
    protected void addErrorMessage(Throwable ex, Object message, Object... args) {
        log.logException(ex, message, args);
    }

    /**
     * @see StatusBar#setMessage(boolean, Object, Object...)
     */
    protected void setStatusBarMessage(boolean busy, Enum<?> message,
            Object... args) {
        StatusBar statusBar = getStatusBar();
        statusBar.setMessage(busy, message, args);
    }

    /**
     * @see StatusBar#setProgress(int, int, Object, Object...)
     */
    protected void setStatusBarProgress(int max, int done, Enum<?> message,
            Object... args) {
        StatusBar statusBar = getStatusBar();
        statusBar.setProgress(max, done, message, args);
    }

    private static final String CAUSE_MESSAGE_ARG = "causeMessage";

}
