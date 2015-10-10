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
package com.anrisoftware.prefdialog.miscswing.logwindowdock;

import static com.anrisoftware.prefdialog.miscswing.actions.AppActionListenerResource.application_error_message;
import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.notNull;

import com.anrisoftware.globalpom.log.AbstractLogger;
import com.anrisoftware.prefdialog.miscswing.statusbar.StatusBar;

/**
 * Logging messages that are shown in the log window dock as nodes.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public class AbstractLogWindowLogger extends AbstractLogger {

    private LogWindowDock logWindowDock;

    private StatusBar statusBar;

    /**
     * Creates a logger for the specified context class.
     *
     * @param contextClass
     *            the context {@link Class}.
     */
    public AbstractLogWindowLogger(
            @SuppressWarnings("rawtypes") Class contextClass) {
        super(contextClass);
    }

    public void setLogWindowDock(LogWindowDock dock) {
        this.logWindowDock = dock;
    }

    public LogWindowDock getLogWindowDock() {
        return logWindowDock;
    }

    public void setStatusBar(StatusBar statusBar) {
        this.statusBar = statusBar;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    @Override
    public <T extends Throwable> T logException(T ex, Object message,
            Object... args) {
        String title = ex.getLocalizedMessage();
        return logException(title, "", ex, message, args);
    }

    /**
     * @param title
     *            the title for the error node.
     *
     * @param descr
     *            the description for the error node.
     *
     * @see #logException(Throwable, Object, Object...)
     */
    public <T extends Throwable> T logException(Object title, Object descr,
            T ex, Object message, Object... args) {
        return logException(title.toString(), descr.toString(), ex, message,
                args);
    }

    /**
     * @param title
     *            the title for the error node.
     *
     * @param descr
     *            the description for the error node.
     *
     * @see #logException(Throwable, Object, Object...)
     */
    public <T extends Throwable> T logException(String title, String descr,
            T ex, Object message, Object... args) {
        notNull(logWindowDock, "logWindowDock");
        notNull(statusBar, "statusBar");
        statusBar.setMessage(false, application_error_message);
        logWindowDock.addErrorMessage(title, format(descr, args), ex);
        return super.logException(ex, message, args);
    }

}
