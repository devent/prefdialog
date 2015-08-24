/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-appdialogs.
 *
 * prefdialog-appdialogs is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-appdialogs is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-appdialogs. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.appdialogs.dialog

import static javax.swing.SwingUtilities.invokeAndWait

import java.awt.Color
import java.awt.Component
import java.awt.Dimension

import javax.swing.BorderFactory
import javax.swing.JDialog
import javax.swing.JPanel

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.globalpom.utils.frametesting.DialogTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.google.inject.Injector

/**
 * @see AppDialog
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
class AppDialogTest {

    @Test
    void "show dialog"() {
        def title = "$NAME/show dialog"
        def size = new Dimension(450, 380)
        def content
        def dialog
        def testing = testingFactory.create([title: title, size: size, setupDialog: { JDialog jdialog, Component component ->
                content = new JPanel()
                content.setBorder BorderFactory.createLineBorder(Color.RED)
                dialog = dialogFactory.create()
                dialog.setDialog jdialog
                dialog.setContent content
                dialog.createDialog()
            }])()
        testing.withFixture({
        })
    }

    static final String NAME = AppDialogTest.class.simpleName

    static Injector injector

    static DialogTestingFactory testingFactory

    static AppDialogFactory dialogFactory

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.injector = AppDialogModule.SingletonHolder.injector.createChildInjector(
                new OnAwtCheckerModule(),
                new FrameTestingModule())
        this.testingFactory = injector.getInstance DialogTestingFactory
        this.dialogFactory = injector.getInstance AppDialogFactory
    }
}
