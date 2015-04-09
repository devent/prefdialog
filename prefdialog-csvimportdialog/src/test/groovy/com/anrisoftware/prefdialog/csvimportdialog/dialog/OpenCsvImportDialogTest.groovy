/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
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

import groovy.util.logging.Slf4j

import java.awt.Dimension

import javax.swing.JFrame
import javax.swing.JPanel

import org.apache.commons.io.FileUtils
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.rules.TemporaryFolder

import com.anrisoftware.globalpom.dataimport.CsvImporter
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.csvimportdialog.dialog.CsvImportDialogModule.SingletonHolder
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelProperties
import com.anrisoftware.prefdialog.csvimportdialog.panelproperties.panelproperties.CsvPanelPropertiesFactory
import com.google.inject.Injector

/**
 * @see OpenCsvImportDialog
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.1
 */
@Slf4j
class OpenCsvImportDialogTest {

    //@Test
    void "show dialog, manually"() {
        String title = "$NAME/show dialog, confirm dialog"
        CsvPanelProperties bean = propertiesFactory.create()
        OpenCsvImportDialog importDialog = injector.getInstance OpenCsvImportDialog
        def testing = testingFactory.create([title: title, size: frameSize, createComponent: { JFrame frame ->
                importDialog.setSize size
                importDialog.setPreviousFile lotto.toURI()
                importDialog.setFrame frame
                importDialog.setSavedProperties bean
                return new JPanel()
            }])()
        testing.withFixture({
            def dialog = importDialog.createDialog()
            importDialog.openDialog dialog
            CsvImporter importer = importDialog.createValue(dialog)
            log.info "Created importer: {}", importer
            assert false : "deactivate manually test"
        })
    }

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder()

    static final String NAME = OpenCsvImportDialogTest.class.simpleName

    static URL LOTTO = OpenCsvImportDialogTest.class.getResource("/com/anrisoftware/prefdialog/csvimportdialog/csvimport/lotto_2001.csv")

    static Injector injector

    static FrameTestingFactory testingFactory

    static frameSize = new Dimension(128, 128)

    static size = new Dimension(600, 480)

    static CsvPanelPropertiesFactory propertiesFactory

    File lotto

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        injector = SingletonHolder.injector.createChildInjector(new FrameTestingModule())
        propertiesFactory = injector.getInstance CsvPanelPropertiesFactory
        testingFactory = injector.getInstance FrameTestingFactory
    }

    @Before
    void setupData() {
        lotto = tmp.newFile("lotto")
        FileUtils.copyURLToFile LOTTO, lotto
    }
}
