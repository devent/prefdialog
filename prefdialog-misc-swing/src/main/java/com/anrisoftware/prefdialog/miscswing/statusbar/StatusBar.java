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
package com.anrisoftware.prefdialog.miscswing.statusbar;

import java.awt.Component;

import javax.inject.Inject;

import org.stringtemplate.v4.AttributeRenderer;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Status bar of the application.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class StatusBar {

    /**
     * Creates the status bar.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     *
     * @return the {@link StatusBar}.
     */
    @OnAwt
    public static StatusBar createStatusBar() {
        return StatusBarModule.getStatusBarFactory().create();
    }

    static final String BUSY_PANEL_CARD = "busyPanelCard";

    static final String PROGRESS_PANEL_CARD = "progressPanelCard";

    static final String PROGRESS_BAR_NAME = "statusbar-progressBar";

    private final UiStatusPanel panel;

    private final STGroup group;

    @Inject
    private StatusBarWorker statusBarWorker;

    private boolean workerExecuted;

    /**
     * @see StatusBarFactory#create()
     */
    @OnAwt
    @Inject
    StatusBar(UiStatusPanel panel) {
        this.panel = panel;
        this.group = new STGroup();
        this.workerExecuted = false;
    }

    /**
     * Adds the attribute renderer to render the status bar messages.
     *
     * @param type
     *            the {@link Class} attribute type.
     *
     * @param renderer
     *            the {@link AttributeRenderer}.
     */
    public void addRenderer(Class<?> type, AttributeRenderer renderer) {
        group.registerRenderer(type, renderer);
    }

    /**
     * Stops the processing of status bar messages.
     */
    public void stopMessages() {
        statusBarWorker.stop();
    }

    /**
     * Sets the message to the status bar. The specified arguments are replaced
     * in the message.
     *
     * @param busy
     *            set to {@code true} to indicate that an operation is on going.
     *
     * @param message
     *            the {@link Object} message.
     *
     * @param args
     *            the {@link Object} arguments.
     */
    public void setMessage(final boolean busy, Object message, Object... args) {
        startTimer();
        final String text = renderMessage(message, args);
        statusBarWorker.add(new StatusBarItem(message) {

            @Override
            public void run() {
                panel.getCardLayout().show(panel.getCardPanel(),
                        BUSY_PANEL_CARD);
                panel.getMessageLabel().setText(text);
                panel.getBusyLabel().setVisible(busy);
                panel.getBusyLabel().setBusy(busy);
            }
        });
    }

    /**
     * Sets the progress message to the status bar with the maximum and already
     * done amount. The specified arguments are replaced in the message.
     *
     * @param max
     *            the maximum amount.
     *
     * @param done
     *            the done amount.
     *
     * @param message
     *            the {@link Object} message.
     *
     * @param args
     *            the {@link Object} arguments.
     */
    public void setProgress(final int max, final int done, Object message,
            Object... args) {
        startTimer();
        final String text = renderMessage(message, args);
        statusBarWorker.add(new StatusBarItem(message) {

            @Override
            public void run() {
                panel.getCardLayout().show(panel.getCardPanel(),
                        PROGRESS_PANEL_CARD);
                panel.getProgressBar().setStringPainted(true);
                panel.getProgressBar().setString(text);
                panel.getProgressBar().setMinimum(0);
                panel.getProgressBar().setMaximum(max);
                panel.getProgressBar().setValue(done);
            }
        });
    }

    /**
     * Sets the message text to the status bar.
     *
     * @param text
     *            the {@link String} text.
     */
    public void setMessageText(final String text) {
        statusBarWorker.add(new StatusBarItem(text) {

            @Override
            public void run() {
                panel.getCardLayout().show(panel.getCardPanel(),
                        BUSY_PANEL_CARD);
                panel.getMessageLabel().setText(text);
                panel.getBusyLabel().setVisible(false);
                panel.getBusyLabel().setBusy(false);
            }
        });
    }

    /**
     * Returns the component of the status bar to be added in the main window.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     *
     * @return the {@link Component}.
     */
    @OnAwt
    public Component getComponent() {
        return panel;
    }

    private String renderMessage(Object message, Object... args) {
        ST st = new ST(group, message.toString());
        for (int i = 0; i < args.length; i += 2) {
            st.add(args[i].toString(), args[i + 1]);
        }
        String text = st.render();
        return text;
    }

    private void startTimer() {
        if (!workerExecuted) {
            statusBarWorker.execute();
            this.workerExecuted = true;
        }
    }

}
