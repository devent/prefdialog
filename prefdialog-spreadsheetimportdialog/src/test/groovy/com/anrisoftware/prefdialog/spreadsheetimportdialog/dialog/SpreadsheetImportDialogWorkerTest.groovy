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

import static com.anrisoftware.prefdialog.spreadsheetimportdialog.utils.Dependencies.*
import groovy.util.logging.Slf4j

import java.awt.Dimension
import java.awt.event.KeyEvent

import javax.swing.JDialog
import javax.swing.SwingUtilities

import org.fest.swing.fixture.DialogFixture
import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.simpledialog.SimpleDialog
import com.anrisoftware.prefdialog.spreadsheetimportdialog.utils.Dependencies

/**
 * @see OpenCsvImportDialog
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.1
 */
@Slf4j
class SpreadsheetImportDialogWorkerTest {

    @Test
    void "show dialog"() {
        def file = copyDocument lottoOds, tmp.newFile()
        def title = "$NAME/show dialog"
        def p = dep.spreadsheetImportPropertiesFactory.create()
        SpreadsheetImportDialogWorker worker
        SpreadsheetImportDialog importDialog
        JDialog jdialog
        def testing = dep.frameTestingFactory.create(title: title, size: frameSize)()
        DialogFixture dialogFix
        testing.withFixture({ FrameFixture it ->
            worker = Dependencies.injector.getInstance SpreadsheetImportDialogWorker
            worker.setFrame it.target
            worker.setLocale Dependencies.locale
            worker.setSize dialogSize
            worker.setImporterFactory dep.importerFactory
            worker.setSavedProperties p
            jdialog = worker.getDialog()
            importDialog = worker.getImportDialog()
            SwingUtilities.invokeLater { jdialog.setVisible true }
        },{ FrameFixture it ->
            dialogFix = it.dialog()
            dialogFix.textBox "file-fileField" setText file.absolutePath
            dialogFix.textBox "file-fileField" pressAndReleaseKeys KeyEvent.VK_ENTER
        }, { FrameFixture it ->
            dialogFix.button "approveButton" click()
        })
        assert importDialog.status == SimpleDialog.Status.APPROVED
        assert importDialog.properties.file.toString() =~ ".*${file.absolutePath}"
    }

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder()

    static Dependencies dep

    static final String NAME = SpreadsheetImportDialogTest.class.simpleName

    static final Dimension frameSize = new Dimension(50, 50)

    static final Dimension dialogSize = new Dimension(400, 566)

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.dep = Dependencies.injector.getInstance Dependencies
    }
}
