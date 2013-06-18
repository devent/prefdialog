package com.anrisoftware.prefdialog.miscswing.comboboxhistory

import java.awt.BorderLayout

import javax.swing.DefaultComboBoxModel
import javax.swing.JComboBox
import javax.swing.JPanel

import org.fest.swing.fixture.FrameFixture
import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see HistoryComboBox
 * @see HistoryComboBoxFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class HistoryComboBoxTest {

	@Test
	void "add items, default items"() {
		def title = "$NAME::add items, default items"
		def comboBox = new JComboBox()
		comboBox.setEditable true
		def box = boxFactory.create comboBox, new DefaultComboBoxModel(), defaultItems
		new TestFrameUtil(title, createPanel(comboBox)).withFixture({ FrameFixture fix ->
			fix.comboBox().selectAllText()
			fix.comboBox().enterText "1"
			Thread.sleep 60*1000
		}, {
		})
	}

	static Injector injector

	static HistoryComboBoxFactory boxFactory

	static Set defaultItems = ["default A", "default B"]

	static final String NAME = HistoryComboBoxTest.class.simpleName

	@BeforeClass
	static void createFactories() {
		injector = createInjector()
		boxFactory = injector.getInstance HistoryComboBoxFactory
	}

	static Injector createInjector() {
		Guice.createInjector(new ComboBoxHistoryModule())
	}

	static JPanel createPanel(def comboBox) {
		def panel = new JPanel(new BorderLayout())
		panel.add comboBox, BorderLayout.NORTH
		panel
	}
}
