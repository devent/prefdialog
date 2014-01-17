/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-csvimportdialog.
 *
 * prefdialog-csvimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-csvimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-csvimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.csvimportdialog.dialog

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialog.*
import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialogModule.*
import static com.anrisoftware.prefdialog.fields.textfield.TextFieldBean.*

import java.awt.Component
import java.awt.Dimension

import javax.swing.JDialog

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.frametesting.DialogTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.google.inject.Injector

/**
 * @see CsvImportDialog
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class CsvImportDialogTest {

    @Test
    void "show dialog"() {
        def title = "$NAME/show dialog"
        def bean = propertiesFactory.create()
        def importDialog
        def testing = testingFactory.create([title: title, size: size, setupDialog: { JDialog dialog, Component component ->
                importDialog = decorateCsvImportDialog(bean, dialog, null)
            }])()
        testing.withFixture({})
    }

    @Test
    void "manually"() {
        def title = "$NAME/manually"
        def bean = propertiesFactory.create()
        def importDialog
        def testing = testingFactory.create([title: title, size: size, setupDialog: { JDialog dialog, Component component ->
                importDialog = decorateCsvImportDialog(bean, dialog, null)
            }])()
        testing.withFixture({
            Thread.sleep 60*1000
            assert false : "deactivate manually test"
        })
    }

    static final String NAME = CsvImportDialogTest.class.simpleName

    static Injector injector

    static CsvImportDialogFactory factory

    static CsvPanelPropertiesFactory propertiesFactory

    static DialogTestingFactory testingFactory

    static size = new Dimension(400, 362)

    @BeforeClass
    static void setupFactories() {
        injector = SingletonHolder.injector.createChildInjector(
                new FrameTestingModule(), new OnAwtCheckerModule())
        factory = injector.getInstance CsvImportDialogFactory
        propertiesFactory = injector.getInstance CsvPanelPropertiesFactory
        testingFactory = injector.getInstance DialogTestingFactory
    }
}
