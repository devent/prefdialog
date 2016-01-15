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
package com.anrisoftware.prefdialog.miscswing.statusbar

import static com.anrisoftware.prefdialog.miscswing.statusbar.StatusBar.*
import static com.anrisoftware.prefdialog.miscswing.statusbar.StatusBarTestMessage.*

import java.awt.BorderLayout

import javax.swing.JFrame

import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.resources.texts.api.Texts
import com.anrisoftware.resources.texts.api.TextsFactory
import com.anrisoftware.resources.texts.defaults.TextsResourcesDefaultModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see StatusBar
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class StatusBarTest {

    @Test
    void "show status bar messages"() {
        StatusBarTestMessage.retrieveTextResources(texts, Locale.getDefault())
        StatusBar statusBar
        def testing = frameTestingFactory.create(createComponent: { frame ->
            statusBar = statusBarFactory.create()
            statusBar.component
        }, setupFrame: { JFrame frame, component ->
            frame.setLayout new BorderLayout()
            frame.add BorderLayout.SOUTH, component
        })()
        FrameFixture fix
        String var = "var"
        testing.withFixture({
            fix = it
            statusBar.setMessage false, non_message
        }, {
            statusBar.setMessage false, status_bar_message_one, "var", var //.
        }, {
            statusBar.setMessage true, status_bar_message_one, "var", var //.
        }, {
            statusBar.setMessage false, status_bar_message_one, "var", var //.
        }, {
            (1..100).each { int i ->
                (1..100).each { int k ->
                    statusBar.setProgress 100, i, status_bar_message_one, "var", "one" //.
                    statusBar.setProgress 100, i, status_bar_message_one, "var", "two" //.
                }
                Thread.sleep 50
            }
            statusBar.setMessage false, non_message
        }, {
            statusBar.stopMessages(); //.
        })
    }

    @Test
    void "show status bar messages, from factory method"() {
        StatusBarTestMessage.retrieveTextResources(texts, Locale.getDefault())
        StatusBar statusBar
        def testing = frameTestingFactory.create(createComponent: { frame ->
            statusBar = createStatusBar()
            statusBar.component
        }, setupFrame: { JFrame frame, component ->
            frame.setLayout new BorderLayout()
            frame.add BorderLayout.SOUTH, component
        })()
        FrameFixture fix
        String var = "var"
        testing.withFixture({
            fix = it
            statusBar.setMessage false, non_message
        }, {
            statusBar.setMessage false, status_bar_message_one, "var", var //.
        })
    }

    static Injector injector

    static StatusBarFactory statusBarFactory

    static FrameTestingFactory frameTestingFactory

    static TextsFactory textsFactory

    static Texts texts

    @BeforeClass
    static void createStatusBar() {
        injector = Guice.createInjector(
                new StatusBarModule(),
                new TextsResourcesDefaultModule(),
                new FrameTestingModule())
        statusBarFactory = injector.getInstance(StatusBarFactory)
        frameTestingFactory = injector.getInstance(FrameTestingFactory)
        textsFactory = injector.getInstance(TextsFactory)
        texts = textsFactory.create("StatusBarTest")
    }
}
