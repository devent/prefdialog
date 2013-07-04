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
