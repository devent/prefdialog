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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.previewpanel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.spreadsheetimportdialog.utils.Dependencies.*
import groovy.transform.CompileStatic

import java.awt.Component
import java.awt.Dimension

import javax.swing.JFrame

import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.spreadsheetimportdialog.utils.Dependencies

/**
 * @see SpreadsheetPreviewPanel
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@CompileStatic
class SpreadsheetPreviewPanelTest {

    @Test
    void "show"() {
        def file = copyDocument lottoOds, tmp.newFile()
        def title = "$NAME::show"
        def p = dep.spreadsheetImportPropertiesFactory.create()
        p.file = file.toURI()
        p.haveHeader = true
        SpreadsheetPreviewPanel previewPanel
        Component container
        def frameTesting = dep.frameTestingFactory.create(title: title, size: frameSize, createComponent: { JFrame frame ->
            previewPanel = dep.spreadsheetPreviewPanelFactory.create()
            previewPanel.createPanel(injector, dep.importerFactory)
            container = previewPanel.getAWTComponent()
            frame.add(container)
        })()
        frameTesting.withFixture({ FrameFixture it ->
            previewPanel.setProperties p
        }, { FrameFixture it ->
            previewPanel.setProperties null
        })
    }

    //@Test
    void "manually"() {
    }

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder()

    static Dependencies dep

    static final String NAME = SpreadsheetPreviewPanelTest.class.simpleName

    static final Dimension frameSize = new Dimension(400, 566)

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.dep = Dependencies.injector.getInstance Dependencies
    }
}
