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

import static com.anrisoftware.prefdialog.miscswing.actions.AbstractAppActionListener._.application_error_description;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Future;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.logwindowdock.LogWindowDock;

/**
 * Retrieves the value from the future task.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public abstract class AbstractAppActionListener implements
        PropertyChangeListener {

    private static final String CAUSE_MESSAGE_ARG = "causeMessage";

    enum _ {

        application_error_description

    }

    @Inject
    private AbstractAppActionLogger log;

    private LogWindowDock logWindowDock;

    /**
     * Sets the log window dock.
     *
     * @param dock
     *            the {@link LogWindowDock}.
     */
    public void setLogWindowDock(LogWindowDock dock) {
        this.logWindowDock = dock;
        log.setLogWindowDock(dock);
    }

    @Override
    public final void propertyChange(PropertyChangeEvent evt) {
        log.checkLogWindowDock(logWindowDock);
        try {
            doPropertyChange(evt);
        } catch (Throwable e) {
            log.logException(e, application_error_description,
                    CAUSE_MESSAGE_ARG, e.getLocalizedMessage());
        }
    }

    /**
     * Adds the exception to the errors window.
     * <p>
     * <h2>AWT Thread</h2>
     * Should be called <i>outside</i> the AWT event dispatch thread.
     * </p>
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
}
