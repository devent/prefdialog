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

	//@Test
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
