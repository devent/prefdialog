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
package com.anrisoftware.prefdialog.miscswing.spreadsheet.navigation

import static javax.swing.SwingUtilities.*

import java.awt.BorderLayout
import java.awt.Component

import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.frametesting.FrameTestingFactory
import com.anrisoftware.globalpom.utils.frametesting.FrameTestingModule
import com.anrisoftware.prefdialog.miscswing.spreadsheet.table.NumbersModel
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
		def title = "$NAME/show"
		def model = new NumbersModel(3, 128)
		def testing = testingFactory.create([title: title, setupFrame: { JFrame frame, Component component ->
				def table = new JTable(model)
				def spreadsheet = tableFactory.create(table)
				def pane = paneFactory.create(new JPanel(), spreadsheet)
				def panel = createTablePanel table, pane.container
				frame.add panel, BorderLayout.CENTER
			}])()
		testing.withFixture({})
	}

	//@Test
	void "manually"() {
		def title = "$NAME/manually"
		def model = new NumbersModel(3, 128)
		def testing = testingFactory.create([title: title, setupFrame: { JFrame frame, Component component ->
				def table = new JTable(model)
				def spreadsheet = tableFactory.create(table)
				def pane = paneFactory.create(new JPanel(), spreadsheet)
				def panel = createTablePanel table, pane.container
				frame.add panel, BorderLayout.CENTER
			}])()
		testing.withFixture({
			Thread.sleep 60*1000
			assert false : "Deactivate manually test"
		})
	}

	static Injector injector

	static SpreadsheetTableFactory tableFactory

	static NavigationPanelFactory paneFactory

	static FrameTestingFactory testingFactory

	static final String NAME = NavigationPanelTest.class.simpleName

	@BeforeClass
	static void createFactories() {
		injector = createInjector()
		paneFactory = injector.getInstance NavigationPanelFactory
		tableFactory = injector.getInstance SpreadsheetTableFactory
		testingFactory = injector.getInstance FrameTestingFactory
	}

	static Injector createInjector() {
		Guice.createInjector(new SpreadsheetTableModule(), new NavigationPanelModule(),
				new FrameTestingModule())
	}

	static JPanel createTablePanel(JTable table, JPanel navigation) {
		def panel = new JPanel(new BorderLayout())
		def scroll = new JScrollPane(table)
		panel.add scroll, BorderLayout.CENTER
		panel.add navigation, BorderLayout.SOUTH
		panel
	}
}
