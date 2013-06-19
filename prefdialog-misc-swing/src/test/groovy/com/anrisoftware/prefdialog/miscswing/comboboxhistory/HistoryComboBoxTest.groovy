package com.anrisoftware.prefdialog.miscswing.comboboxhistory

import java.awt.BorderLayout
import java.awt.event.KeyEvent

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
		def fixbox
		def box = boxFactory.create comboBox, defaultItems
		new TestFrameUtil(title, createPanel(comboBox)).withFixture({ FrameFixture fix ->
			fixbox = fix.comboBox()
			fixbox.selectAllText()
			fixbox.enterText "1"
			fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
			assert box.model.getElementAt(0) == "1"
			assert box.model.getElementAt(1) == "default A"
			assert box.model.getElementAt(2) == "default B"
		}, {
			fixbox.selectAllText()
			fixbox.enterText "default A"
			fixbox.pressAndReleaseKeys KeyEvent.VK_ENTER
			assert box.model.getElementAt(0) == "1"
			assert box.model.getElementAt(1) == "default A"
			assert box.model.getElementAt(2) == "default B"
		})
	}

	@Test
	void "manually"() {
		def title = "$NAME::manually"
		def comboBox = new JComboBox()
		comboBox.setEditable true
		def box = boxFactory.create comboBox, defaultItems
		new TestFrameUtil(title, createPanel(comboBox)).withFixture({
			Thread.sleep 600*1000
			assert false : "manually test"
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
