/*
 * Copyright 2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-spreadsheetimportdialog.
 *
 * prefdialog-spreadsheetimportdialog is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-spreadsheetimportdialog is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-spreadsheetimportdialog. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.csvimportdialog.core.FieldTestUtils.*
import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialog.*
import static com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialogModule.*

import java.awt.Component
import java.awt.Dimension

import javax.swing.JDialog

import org.junit.BeforeClass
import org.junit.Test

import bibliothek.gui.dock.common.theme.ThemeMap

import com.anrisoftware.globalpom.utils.frametesting.DialogTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory
import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwtCheckerModule
import com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog.SpreadsheetImportDialogFactory;
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
    void "set theme"() {
        def title = "$NAME/set theme"
        def bean = propertiesFactory.create()
        def importDialog
        def testing = testingFactory.create([title: title, size: size, setupDialog: { JDialog dialog, Component component ->
                importDialog = decorateCsvImportDialog(bean, dialog, null)
                importDialog.setTheme ThemeMap.KEY_ECLIPSE_THEME
            }])()
        testing.withFixture({
            //
            importDialog.setTheme ThemeMap.KEY_SMOOTH_THEME
        }, {
            //
            importDialog.setTheme ThemeMap.KEY_BUBBLE_THEME
        }, {
            //
            importDialog.setTheme ThemeMap.KEY_BASIC_THEME
        }, {
            //
            importDialog.setTheme ThemeMap.KEY_FLAT_THEME
        })
    }

    @Test
    void "save load layout"() {
        def title = "$NAME/save load layout"
        def bean = propertiesFactory.create()
        def importDialog
        def ostream = new ByteArrayOutputStream()
        def layoutName = "default"
        def testing = testingFactory.create([title: title, size: size, setupDialog: { JDialog dialog, Component component ->
                importDialog = decorateCsvImportDialog(bean, dialog, null)
                importDialog.setTheme ThemeMap.KEY_ECLIPSE_THEME
            }])()
        testing.withFixture({
            //
            importDialog.saveLayout layoutName, ostream
        }, {
            //
            importDialog.loadLayout layoutName, new ByteArrayInputStream(ostream.toByteArray())
        })
    }

    //@Test
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

    static SpreadsheetImportDialogFactory factory

    static CsvPanelPropertiesFactory propertiesFactory

    static DialogTestingFactory testingFactory

    static size = new Dimension(600, 362)

    @BeforeClass
    static void setupFactories() {
        injector = SingletonHolder.injector.createChildInjector(
                new FrameTestingModule(), new OnAwtCheckerModule())
        factory = injector.getInstance SpreadsheetImportDialogFactory
        propertiesFactory = injector.getInstance CsvPanelPropertiesFactory
        testingFactory = injector.getInstance DialogTestingFactory
    }
}
