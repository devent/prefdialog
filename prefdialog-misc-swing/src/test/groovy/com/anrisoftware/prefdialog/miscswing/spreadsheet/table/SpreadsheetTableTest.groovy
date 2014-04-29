/*
 * Copyright 2013-2014 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-misc-swing.
 *
 * prefdialog-misc-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-misc-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-misc-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.anrisoftware.prefdialog.miscswing.spreadsheet.table

import static com.anrisoftware.globalpom.utils.TestFrameUtil.*
import static javax.swing.SwingUtilities.*

import java.awt.BorderLayout

import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.ListSelectionModel

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see SpreadsheetTable
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SpreadsheetTableTest {

    @Test
    void "show"() {
        def title = "$NAME::show"
        def model = new NumbersModel(3, 128)
        def table
        def spreadsheet
        def panel
        invokeAndWait {
            table = new SheetTable(model)
            spreadsheet = factory.create(table)
            panel = createTablePanel(table)
        }
        new TestFrameUtil(title, panel).withFixture({})
    }

    //@Test
    void "manually"() {
        //setLookAndFeel GTK_LOOK_AND_FEEL
        //setLookAndFeel SUBSTANCE_BUSINESS_LOOK_AND_FEEL
        //setLookAndFeel NIMBUS_LOOK_AND_FEEL
        def title = "$NAME::manually"
        def model = new NumbersModel(3, 128)
        def table
        def spreadsheet
        def panel
        invokeAndWait {
            table = new SheetTable(model)
            table.setCellSelectionEnabled(true)
            table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION)
            spreadsheet = factory.create(table)
            panel = createTablePanel(table)
        }
        new TestFrameUtil(title, panel).withFixture({
            Thread.sleep 60*1000
            assert false : "Deactivate manually test"
        })
    }

    static Injector injector

    static SpreadsheetTableFactory factory

    static final String NAME = SpreadsheetTableTest.class.simpleName

    @BeforeClass
    static void createFactories() {
        injector = createInjector()
        factory = injector.getInstance SpreadsheetTableFactory
    }

    static Injector createInjector() {
        Guice.createInjector(new SpreadsheetTableModule())
    }

    static JPanel createTablePanel(def table) {
        def panel = new JPanel(new BorderLayout())
        def scroll = new JScrollPane(table)
        panel.add scroll, BorderLayout.CENTER
        panel
    }
}
