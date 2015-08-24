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
package com.anrisoftware.prefdialog.appdialogs.confirmationdialogs

import static com.anrisoftware.globalpom.utils.TestUtils.*

import java.awt.Component
import java.awt.Dimension

import javax.swing.JDialog

import org.fest.swing.fixture.DialogFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.frametesting.DialogTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog
import com.google.inject.Injector

/**
 * @see UnsavedConfirmationDialog
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
class UnsavedConfirmationDialogTest {

    @Test
    void "show Unsaved dialog, discard dialog"() {
        def title = "$NAME/show Unsaved dialog, discard dialog"
        def size = new Dimension(450, 380)
        def dialog
        def confirmationDialog
        def createDialog = {
            dialog = new JDialog()
            confirmationDialog = dialogFactory.create()
            confirmationDialog.setDialog dialog
            confirmationDialog.createDialog()
            return confirmationDialog.getDialog()
        }
        def setupDialog = { JDialog d, Component c ->
            d.pack()
        }
        def testing = testingFactory.create([title: title, size: size, createDialog: createDialog, setupDialog: setupDialog ])()
        testing.withFixture({ DialogFixture fix ->
            fix.button SimpleDialog.RESTORE_BUTTON_NAME click()
        })
        assert confirmationDialog.isDiscard()
    }

    @Test
    void "show Unsaved dialog, save dialog"() {
        def title = "$NAME/show Unsaved dialog, save dialog"
        def size = new Dimension(450, 380)
        def dialog
        def confirmationDialog
        def createDialog = {
            dialog = new JDialog()
            confirmationDialog = dialogFactory.create()
            confirmationDialog.setDialog dialog
            confirmationDialog.createDialog()
            return confirmationDialog.getDialog()
        }
        def setupDialog = { JDialog d, Component c ->
            d.pack()
        }
        def testing = testingFactory.create([title: title, size: size, createDialog: createDialog, setupDialog: setupDialog ])()
        testing.withFixture({ DialogFixture fix ->
            fix.button SimpleDialog.APPROVE_BUTTON_NAME click()
        })
        assert confirmationDialog.isSave()
    }

    static final String NAME = UnsavedConfirmationDialogTest.class.simpleName

    static Injector injector

    static DialogTestingFactory testingFactory

    static UnsavedConfirmationDialogFactory dialogFactory

    @BeforeClass
    static void createFactory() {
        toStringStyle
        this.injector = ConfirmationDialogModule.confirmationDialogInjector.createChildInjector(
                new OnAwtCheckerModule(),
                new FrameTestingModule())
        this.testingFactory = injector.getInstance DialogTestingFactory
        this.dialogFactory = injector.getInstance UnsavedConfirmationDialogFactory
    }
}
