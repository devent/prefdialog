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
import static com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog.SpreadsheetImportDialog.*
import static com.anrisoftware.prefdialog.spreadsheetimportdialog.utils.Dependencies.*
import static javax.swing.SwingUtilities.*
import groovy.transform.CompileStatic

import java.awt.Component
import java.awt.Dimension
import java.awt.event.KeyEvent

import javax.swing.JDialog
import javax.swing.SwingUtilities

import org.fest.swing.fixture.DialogFixture
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import bibliothek.gui.dock.common.theme.ThemeMap

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog
import com.anrisoftware.prefdialog.spreadsheetimportdialog.utils.Dependencies

/**
 * @see SpreadsheetImportDialog
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@CompileStatic
class SpreadsheetImportDialogTest {

    @Test
    void "show dialog"() {
        def file = copyDocument lottoOds, tmp.newFile()
        def title = "$NAME/show dialog"
        def p = dep.spreadsheetImportPropertiesFactory.create()
        SpreadsheetImportDialog importDialog
        def testing = dep.dialogTestingFactory.create(title: title, size: frameSize,
        setupDialog: { JDialog dialog, Component component ->
            importDialog = decorateSpreadsheetImportDialog(p, dialog, null, dep.locale, dep.importerFactory)
        })()
        testing.withFixture({ DialogFixture it ->
            it.textBox "file-fileField" setText file.absolutePath
            it.textBox "file-fileField" pressAndReleaseKeys KeyEvent.VK_ENTER
        }, { DialogFixture it ->
            it.button "approveButton" click()
        })
        assert importDialog.status == SimpleDialog.Status.APPROVED
        assert importDialog.properties.file.toString() =~ ".*${file.absolutePath}"
    }

    @Test
    void "set theme"() {
        def title = "$NAME/set theme"
        def p = dep.spreadsheetImportPropertiesFactory.create()
        SpreadsheetImportDialog importDialog
        def testing = dep.dialogTestingFactory.create(title: title, size: frameSize,
        setupDialog: { JDialog dialog, Component component ->
            importDialog = decorateSpreadsheetImportDialog(p, dialog, null, dep.locale, dep.importerFactory)
            importDialog.setTheme ThemeMap.KEY_ECLIPSE_THEME
        })()
        testing.withFixture({
            //
            invokeAndWait { importDialog.setTheme ThemeMap.KEY_SMOOTH_THEME }
        }, {
            //
            invokeAndWait { importDialog.setTheme ThemeMap.KEY_BUBBLE_THEME }
        }, {
            //
            invokeAndWait { importDialog.setTheme ThemeMap.KEY_BASIC_THEME }
        }, {
            //
            invokeAndWait { importDialog.setTheme ThemeMap.KEY_FLAT_THEME }
        })
    }

    @Test
    void "save load layout"() {
        def title = "$NAME/save load layout"
        def p = dep.spreadsheetImportPropertiesFactory.create()
        SpreadsheetImportDialog importDialog
        def ostream = new ByteArrayOutputStream()
        def layoutName = "default"
        def testing = dep.dialogTestingFactory.create(title: title, size: frameSize,
        setupDialog: { JDialog dialog, Component component ->
            importDialog = decorateSpreadsheetImportDialog(p, dialog, null, dep.locale, dep.importerFactory)
            importDialog.setTheme ThemeMap.KEY_ECLIPSE_THEME
        })()
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
        def p = dep.spreadsheetImportPropertiesFactory.create()
        SpreadsheetImportDialog importDialog
        def ostream = new ByteArrayOutputStream()
        def layoutName = "default"
        def testing = dep.dialogTestingFactory.create(title: title, size: frameSize,
        setupDialog: { JDialog dialog, Component component ->
            importDialog = decorateSpreadsheetImportDialog(p, dialog, null, dep.locale, dep.importerFactory)
            importDialog.setTheme ThemeMap.KEY_ECLIPSE_THEME
        })()
        testing.withFixture({
            Thread.sleep 60*1000; assert false : "Thread.sleep"
        })
    }

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder()

    static Dependencies dep

    static final String NAME = SpreadsheetImportDialogTest.class.simpleName

    static final Dimension frameSize = new Dimension(400, 566)

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.dep = Dependencies.injector.getInstance Dependencies
    }

}
