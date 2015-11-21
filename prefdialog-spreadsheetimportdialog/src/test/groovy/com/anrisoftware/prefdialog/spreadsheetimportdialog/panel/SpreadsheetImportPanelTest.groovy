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
package com.anrisoftware.prefdialog.spreadsheetimportdialog.panel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import groovy.transform.CompileStatic

import java.awt.Dimension
import java.awt.event.KeyEvent

import javax.swing.JFrame
import javax.swing.JPanel

import org.apache.commons.io.IOUtils
import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.spreadsheetimportdialog.utils.Dependencies

/**
 * @see SpreadsheetImportPanel
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@CompileStatic
class SpreadsheetImportPanelTest {

    @Test
    void "show"() {
        def file = tmp.newFile()
        IOUtils.copy Dependencies.lottoOds.openStream(), new FileOutputStream(file)
        def title = "$NAME::show"
        def p = dep.spreadsheetPanelPropertiesFactory.create()
        def field = dep.spreadsheetImportPanelFactory.create(new JPanel(), p, dep.importerFactory).createPanel()
        def container = field.getAWTComponent()
        def frameTesting = dep.frameTestingFactory.create(title: title, size: frameSize, createComponent: { JFrame frame ->
            frame.add(container)
        })()
        frameTesting.withFixture({ FrameFixture it ->
            it.textBox "file-fileField" setText file.absolutePath
            it.textBox "file-fileField" pressAndReleaseKeys KeyEvent.VK_ENTER
            Thread.sleep 60*1000; assert false : "Thread.sleep"
        })
    }

    //@Test
    void "manually"() {
    }

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder()

    static Dependencies dep

    static final String NAME = SpreadsheetImportPanelTest.class.simpleName

    static final Dimension frameSize = new Dimension(400, 566)

    @BeforeClass
    static void setupFactories() {
        TestUtils.toStringStyle
        this.dep = Dependencies.injector.getInstance Dependencies
    }
}
