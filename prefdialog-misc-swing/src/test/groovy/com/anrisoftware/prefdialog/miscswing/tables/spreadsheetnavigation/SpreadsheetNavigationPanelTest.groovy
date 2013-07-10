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
package com.anrisoftware.prefdialog.miscswing.tables.spreadsheetnavigation

import javax.swing.JPanel
import javax.swing.JTable

import org.junit.BeforeClass

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.miscswing.tables.spreadsheet.NumbersModel
import com.anrisoftware.prefdialog.miscswing.tables.spreadsheet.SpreadsheetTableFactory
import com.anrisoftware.prefdialog.miscswing.tables.spreadsheet.SpreadsheetTableModule
import com.anrisoftware.prefdialog.miscswing.tables.spreadsheet.ViewRange
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see SpreadsheetNavigationPanel
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
class SpreadsheetNavigationPanelTest {

	//@Test
	void "manually"() {
		def title = "$NAME::manually"
		def table = new JTable()
		def range = new ViewRange(40)
		def model = new NumbersModel(3, 20)
		def spreadsheet = tableFactory.create(table, model, range)
		def pane = paneFactory.create(new JPanel(), spreadsheet)

		new TestFrameUtil(title, pane.container).withFixture({
			Thread.sleep 60*1000
			assert false : "Deactivate manually test"
		})
	}

	static Injector injector

	static SpreadsheetTableFactory tableFactory

	static SpreadsheetNavigationPanelFactory paneFactory

	static final String NAME = SpreadsheetNavigationPanelTest.class.simpleName

	@BeforeClass
	static void createFactories() {
		injector = createInjector()
		paneFactory = injector.getInstance SpreadsheetNavigationPanelFactory
		tableFactory = injector.getInstance SpreadsheetTableFactory
	}

	static Injector createInjector() {
		Guice.createInjector(new SpreadsheetTableModule(), new SpreadsheetNavigationPanelModule())
	}
}
