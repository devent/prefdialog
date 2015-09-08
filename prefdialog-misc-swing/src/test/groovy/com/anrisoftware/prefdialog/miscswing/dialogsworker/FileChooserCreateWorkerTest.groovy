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
package com.anrisoftware.prefdialog.miscswing.dialogsworker

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.google.inject.Guice.createInjector
import static javax.swing.SwingUtilities.*
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.awt.Component

import javax.inject.Inject
import javax.swing.JFrame

import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.google.inject.Injector

/**
 * @see FileChooserCreateWorker
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3,2
 */
@Slf4j
@CompileStatic
class FileChooserCreateWorkerTest {

    @Test
    void "show file choser"() {
        FileChooserCreateWorker dialog
        JFrame frame
        def frameTesting = dep.frameTestingFactory.create(title: "$NAME/show file choser", setupFrame: { JFrame jframe, Component component ->
            frame = jframe
            dialog = Dependencies.injector.getInstance(FileChooserCreateWorker)
            dialog.setLocale dep.locale
            dialog.setDialogTitle("Open File")
        })()
        frameTesting.withFixture({ FrameFixture it ->
            def fileChoser = dialog.getDialog()
            invokeLater { fileChoser.showOpenDialog(frame) }
            def dialogFix = it.fileChooser()
            dialogFix.cancel()
            //.
        }, {
            //.
            // Thread.sleep 60000 //.
        })
    }

    static class Dependencies {

        static Injector injector = createInjector(
        new FrameTestingModule(),
        new OnAwtCheckerModule(),
        new DialogsWorkerModule())

        @Inject
        FrameTestingFactory frameTestingFactory

        Locale locale = Locale.US
    }

    static Dependencies dep

    static NAME = FileChooserCreateWorkerTest.class.getSimpleName()

    @BeforeClass
    static void createFactory() {
        toStringStyle
        this.dep = Dependencies.injector.getInstance Dependencies
    }
}
