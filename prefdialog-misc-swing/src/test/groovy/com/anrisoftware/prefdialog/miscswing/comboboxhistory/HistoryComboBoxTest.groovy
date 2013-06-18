package com.anrisoftware.prefdialog.miscswing.comboboxhistory

import java.awt.BorderLayout

import javax.swing.DefaultComboBoxModel
import javax.swing.JComboBox
import javax.swing.JPanel

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * @see HistoryComboBoxModel
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class HistoryComboBoxTest {

	@Test
	void "add items, default items"() {
		def title = "$NAME::add items, default items"
		def model = modelFactory.create new DefaultComboBoxModel(), defaultItems
		def comboBox = new JComboBox(model)
		new TestFrameUtil(title, createPanel(comboBox)).withFixture({
			model.addElement "1"
			model.addElement "2"
			model.addElement "3"
			model.addElement "4"
			model.addElement "5"
			model.addElement "6"
			model.addElement "7"
			model.addElement "8"
		}, {
			model.getElementAt(0) == "8"
			model.getElementAt(1) == "7"
			model.getElementAt(2) == "6"
			model.getElementAt(3) == "5"
			model.getElementAt(4) == "4"
			model.getElementAt(5) == "default A"
			model.getElementAt(6) == "default B"
		})
	}

	static Injector injector

	static HistoryComboBoxModelFactory modelFactory

	static List defaultItems = ["default A", "default B"]

	static final String NAME = HistoryComboBoxTest.class.simpleName

	@BeforeClass
	static void createFactories() {
		injector = createInjector()
		modelFactory = injector.getInstance HistoryComboBoxModelFactory
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
