/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-dialog.
 *
 * prefdialog-dialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-dialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-dialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.simpledialog

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.simpledialog.SimpleDialogBean.*
import static javax.swing.SwingUtilities.invokeAndWait

import java.awt.Component

import javax.swing.JDialog
import javax.swing.JPanel

import org.fest.swing.fixture.DialogFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.globalpom.utils.frametesting.DialogTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog.Status
import com.anrisoftware.prefdialog.simpledialog.SimpleDialogModule.SingletonHolder
import com.anrisoftware.resources.texts.api.Texts
import com.anrisoftware.resources.texts.api.TextsFactory
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see SimpleDialog
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SimpleDialogTest {

    @Test
    void "cancel dialog"() {
        def title = "$NAME/cancel dialog"
        def simpleDialog = factory.create()
        def testing = testingFactory.create([title: title, setupDialog: { JDialog dialog, Component component ->
                simpleDialog.setFieldsPanel new JPanel()
                simpleDialog.setDialog dialog
                simpleDialog.setTexts texts
                simpleDialog.createDialog()
            }])()
        testing.withFixture({ DialogFixture fix ->
            fix.button "cancelButton" click()
        })
        assert simpleDialog.status == Status.CANCELED
    }

    @Test
    void "approve dialog"() {
        def title = "$NAME/approve dialog"
        def simpleDialog = factory.create()
        def testing = testingFactory.create([title: title, setupDialog: { JDialog dialog, Component component ->
                simpleDialog.setFieldsPanel new JPanel()
                simpleDialog.setDialog dialog
                simpleDialog.setTexts texts
                simpleDialog.createDialog()
            }])()
        testing.withFixture({ DialogFixture fix ->
            fix.button "approveButton" click()
        })
        assert simpleDialog.status == Status.APPROVED
    }

    @Test
    void "hide approval, restore button"() {
        def title = "$NAME/hide approval, restore button"
        def simpleDialog = factory.create()
        def testing = testingFactory.create([title: title, setupDialog: { JDialog dialog, Component component ->
                simpleDialog.setFieldsPanel new JPanel()
                simpleDialog.setDialog dialog
                simpleDialog.setTexts texts
                simpleDialog.setShowRestoreButton(false)
                simpleDialog.setShowApproveButton(false)
                simpleDialog.createDialog()
            }])()
        testing.withFixture({
            invokeAndWait {
                simpleDialog.setShowApproveButton(true)
                simpleDialog.setShowRestoreButton(true)
            }
        }, {
            invokeAndWait {
                simpleDialog.setShowRestoreButton(false)
                simpleDialog.setShowApproveButton(false)
            }
        }, { DialogFixture fix ->
            fix.button "cancelButton" click()
        })
        assert simpleDialog.status == Status.CANCELED
    }

    @Test
    void "hide restore button"() {
        def title = "$NAME/hide restore button"
        def simpleDialog = factory.create()
        def testing = testingFactory.create([title: title, setupDialog: { JDialog dialog, Component component ->
                simpleDialog.setFieldsPanel new JPanel()
                simpleDialog.setDialog dialog
                simpleDialog.setTexts texts
                simpleDialog.setShowRestoreButton(false)
                simpleDialog.createDialog()
            }])()
        testing.withFixture({
            invokeAndWait {
                simpleDialog.setShowRestoreButton(true) //.
            }
        }, {
            invokeAndWait {
                simpleDialog.setShowRestoreButton(false) //.
            }
        }, { DialogFixture fix ->
            fix.button "approveButton" click()
        })
        assert simpleDialog.status == Status.APPROVED
    }

    //@Test
    void "manually"() {
        def title = "$NAME/manually"
        def simpleDialog = factory.create()
        def testing = testingFactory.create([title: title, setupDialog: { JDialog dialog, Component component ->
                simpleDialog.setFieldsPanel new JPanel()
                simpleDialog.setDialog dialog
                simpleDialog.setTexts texts
                simpleDialog.createDialog()
            }])()
        testing.withFixture({
            Thread.sleep 60000
            assert false : "manually test"
        })
    }

    static Injector injector

    static final String NAME = SimpleDialogTest.class.simpleName

    static DialogTestingFactory testingFactory

    static SimpleDialogFactory factory

    static TextsFactory textsFactory

    static Texts texts

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        injector = createInjector()
        testingFactory = injector.getInstance DialogTestingFactory
        factory = injector.getInstance SimpleDialogFactory
        textsFactory = injector.getInstance(TextsFactory)
        texts = textsFactory.create(SimpleDialog.class.getSimpleName())
    }

    private static createInjector() {
        Guice.createInjector([
            SingletonHolder.modules,
            new FrameTestingModule(),
            new OnAwtCheckerModule()
        ].flatten())
    }
}
