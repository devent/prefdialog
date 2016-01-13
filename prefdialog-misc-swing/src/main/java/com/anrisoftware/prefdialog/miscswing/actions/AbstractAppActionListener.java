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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;
import java.util.concurrent.Future;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.miscswing.logwindowdock.LogWindowDock;
import com.anrisoftware.prefdialog.miscswing.statusbar.StatusBar;
import com.anrisoftware.resources.texts.api.Texts;

/**
 * Retrieves the value from the future task.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.2
 */
public abstract class AbstractAppActionListener implements
        PropertyChangeListener {

    @Inject
    private AbstractAppActionLogger log;

    private Texts texts;

    private Locale locale;

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
     *
     * @since 3.3
     */
    public LogWindowDock getLogWindowDock() {
        return log.getLogWindowDock();
    }

    /**
     * Sets the status bar.
     *
     * @param statusBar
     *            the {@link StatusBar}.
     *
     * @since 3.3
     */
    public void setStatusBar(StatusBar statusBar) {
        log.setStatusBar(statusBar);
    }

    /**
     * Sets the texts resources.
     *
     * @param texts
     *            the {@link Texts}.
     *
     * @since 3.6
     */
    public void setTexts(Texts texts) {
        this.texts = texts;
        updateTexts();
    }

    /**
     * Sets the texts resources locale.
     *
     * @param locale
     *            the {@link Locale}.
     *
     * @since 3.6
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        updateTexts();
    }

    /**
     * Returns the status bar.
     *
     * @return the {@link StatusBar}.
     *
     * @since 3.3
     */
    public StatusBar getStatusBar() {
        return log.getStatusBar();
    }

    @Override
    public final void propertyChange(PropertyChangeEvent evt) {
        notNull(getStatusBar(), "statusBar");
        notNull(getLogWindowDock(), "logWindowDock");
        try {
            doPropertyChange(evt);
        } catch (Throwable e) {
            log.logException(e, application_error_description,
                    CAUSE_MESSAGE_ARG, e.getLocalizedMessage());
        }
    }

    /**
     * Do the application action.
     *
     * @see PropertyChangeListener#propertyChange(PropertyChangeEvent)
     */
    protected abstract void doPropertyChange(PropertyChangeEvent evt)
            throws Exception;

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
     * Returns the future task from the property change event.
     *
     * @param evt
     *            the {@link PropertyChangeEvent} that have the source set to
     *            the future task.
     *
     * @return the {@link Future} task.
     *
     * @since 3.6
     */
    @SuppressWarnings("unchecked")
    public static <T> Future<T> asFuture(PropertyChangeEvent evt) {
        return (Future<T>) evt.getSource();
    }

    private static final String CAUSE_MESSAGE_ARG = "causeMessage";

    private void updateTexts() {
        if (texts == null || locale == null) {
            return;
        }
        AppActionListenerResource.loadTexts(texts, locale);
    }

}
