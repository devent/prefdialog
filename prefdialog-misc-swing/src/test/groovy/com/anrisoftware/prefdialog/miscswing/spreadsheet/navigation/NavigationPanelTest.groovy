/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
package com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation

import static javax.swing.SwingUtilities.*

import java.awt.BorderLayout

import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.miscswing.spreadsheet.table.NumbersModel
import com.anrisoftware.prefdialog.miscswing.spreadsheet.table.SheetTable
import com.anrisoftware.prefdialog.miscswing.spreadsheet.table.SpreadsheetTableFactory
import com.anrisoftware.prefdialog.miscswing.spreadsheet.table.SpreadsheetTableModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see SpreadsheetNavigationPanel
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class NavigationPanelTest {

	@Test
	void "show"() {
		def title = "$NAME::show"
		def table
		def spreadsheet
		def pane
		def panel
		def model = new NumbersModel(3, 128)
		invokeAndWait {
			table = new JTable(model)
			spreadsheet = tableFactory.create(table)
			pane = paneFactory.create(new JPanel(), spreadsheet)
			panel = createTablePanel(table, pane.container)
		}
		new TestFrameUtil(title, panel).withFixture({ })
	}

	@Test
	void "manually"() {
		def title = "$NAME::manually"
		def table
		def spreadsheet
		def pane
		def panel
		def model = new NumbersModel(3, 128)
		invokeAndWait {
			table = new SheetTable(model)

			spreadsheet = tableFactory.create(table)
			pane = paneFactory.create(new JPanel(), spreadsheet)
			pane.minimumRow = 0
			pane.minimumColumn = 0
			panel = createTablePanel(table, pane.container)
		}
		new TestFrameUtil(title, panel).withFixture({
			Thread.sleep 60*1000
			assert false : "Deactivate manually test"
		})
	}

	static Injector injector

	static SpreadsheetTableFactory tableFactory

	static NavigationPanelFactory paneFactory

	static final String NAME = NavigationPanelTest.class.simpleName

	@BeforeClass
	static void createFactories() {
		injector = createInjector()
		paneFactory = injector.getInstance NavigationPanelFactory
		tableFactory = injector.getInstance SpreadsheetTableFactory
	}

	static Injector createInjector() {
		Guice.createInjector(new SpreadsheetTableModule(), new NavigationPanelModule())
	}

	static JPanel createTablePanel(def table, def navigation) {
		def panel = new JPanel(new BorderLayout())
		def scroll = new JScrollPane(table)
		panel.add scroll, BorderLayout.CENTER
		panel.add navigation, BorderLayout.SOUTH
		panel
	}
}
