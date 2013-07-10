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
 * @see HistoryComboBoxModelFactory
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class HistoryComboBoxModelTest {

	@Test
	void "add, remove items, default items"() {
		def title = "$NAME::add, remove items, default items"
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
		}, {
			model.addElement "7"
			model.addElement "8"
			model.getElementAt(0) == "8"
			model.getElementAt(1) == "7"
			model.getElementAt(2) == "6"
			model.getElementAt(3) == "5"
			model.getElementAt(4) == "4"
			model.getElementAt(5) == "default A"
			model.getElementAt(6) == "default B"
		}, {
			model.removeElement "6"
			model.removeElement "5"
			model.getElementAt(0) == "8"
			model.getElementAt(1) == "7"
			model.getElementAt(2) == "4"
			model.getElementAt(3) == "default A"
			model.getElementAt(4) == "default B"
		})
	}

	static Injector injector

	static HistoryComboBoxModelFactory modelFactory

	static Set defaultItems = ["default A", "default B"]

	static final String NAME = HistoryComboBoxModelTest.class.simpleName

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
