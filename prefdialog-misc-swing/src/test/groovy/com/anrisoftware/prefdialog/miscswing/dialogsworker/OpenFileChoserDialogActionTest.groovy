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
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.anrisoftware.prefdialog.miscswing.filechoosers.OpenFileDialogModel
import com.google.inject.Injector

/**
 * @see OpenFileChoserDialogAction
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3,2
 */
@Slf4j
@CompileStatic
class OpenFileChoserDialogActionTest {

    @Test
    void "cancel file choser"() {
        OpenFileChooserDialogAction action
        OpenFileDialogModel model
        JFrame frame
        File file
        def frameTesting = dep.frameTestingFactory.create(title: "$NAME/cancel file choser", setupFrame: { JFrame jframe, Component component ->
            frame = jframe
            action = Dependencies.injector.getInstance(OpenFileChooserDialogAction)
            model = Dependencies.injector.getInstance(OpenFileDialogModel)
            action.setParent jframe
            action.setLocale dep.locale
            action.setDialogTitle("Open File")
            action.setModel model
        })()
        frameTesting.withFixture({ FrameFixture it ->
            Thread.start {
                file = (File) action.call()
            }
            def dialogFix = it.fileChooser()
            dialogFix.cancel()
            //.
        })
        assert file == null
    }

    @Test
    void "approve file choser"() {
        OpenFileChooserDialogAction action
        OpenFileDialogModel model
        JFrame frame
        File tmp = folder.newFile()
        File file
        def frameTesting = dep.frameTestingFactory.create(title: "$NAME/approve file choser", setupFrame: { JFrame jframe, Component component ->
            frame = jframe
            action = Dependencies.injector.getInstance(OpenFileChooserDialogAction)
            model = Dependencies.injector.getInstance(OpenFileDialogModel)
            action.setParent jframe
            action.setFile tmp
            action.setLocale dep.locale
            action.setDialogTitle("Open File")
            action.setModel model
        })()
        frameTesting.withFixture({ FrameFixture it ->
            Thread.start {
                file = (File) action.call()
            }
            def dialogFix = it.fileChooser()
            dialogFix.selectFile tmp
            dialogFix.approve()
            //.
        })
        assert file == tmp
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

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    static Dependencies dep

    static NAME = OpenFileChoserDialogActionTest.class.getSimpleName()

    @BeforeClass
    static void createFactory() {
        toStringStyle
        this.dep = Dependencies.injector.getInstance Dependencies
    }
}
