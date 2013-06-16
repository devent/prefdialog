package com.anrisoftware.prefdialog.miscswing.tables.spreadsheet

import java.awt.BorderLayout

import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable

import org.junit.BeforeClass

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see SpreadsheetTable
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class SpreadsheetTableTest {

	//@Test
	void "manually"() {
		def title = "$NAME::manually"
		def table = new JTable()
		def range = new ViewRange(40)
		def model = new NumbersModel(3, 20)
		def spreadsheet = factory.create(table, model, range)
		new TestFrameUtil(title, createTablePanel(table)).withFixture({
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
