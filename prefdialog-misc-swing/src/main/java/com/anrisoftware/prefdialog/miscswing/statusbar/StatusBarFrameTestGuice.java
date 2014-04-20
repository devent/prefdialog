/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
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

import static javax.swing.SwingUtilities.invokeLater;

import javax.inject.Inject;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Shows a frame to test the status bar.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public class StatusBarFrameTestGuice {

    public static void main(final String[] args) {
        injector = Guice.createInjector(new StatusBarModule());
        invokeLater(new Runnable() {

            @Override
            public void run() {
                instance = injector.getInstance(StatusBarFrameTestGuice.class);
                instance.startApp(args);
            }
        });
    }

    private static Injector injector;

    private static StatusBarFrameTestGuice instance;

    @Inject
    private StatusBarFrame frame;

    @Inject
    private StatusBarFactory statusBarFactory;

    private StatusBar statusBar;

    private void startApp(String[] args) {
        this.statusBar = statusBarFactory.create();
        frame.setArgs(new Object[] { "var1", "foo", "var2", "bar" });
        frame.setStatusBar(statusBar);
        frame.openFrame();
    }

}
