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
package com.anrisoftware.prefdialog.miscswing.lists

import java.awt.BorderLayout

import javax.swing.JList
import javax.swing.JMenuItem
import javax.swing.JPanel
import javax.swing.JPopupMenu
import javax.swing.JScrollPane

import org.fest.swing.fixture.FrameFixture

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * @see PopupMenuList
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class PopupMenuListTest {

	//@Test
	void "manually"() {
		String title = "PopupMenuListTest::manually"
		def frame = createFrameWithList title
		frame.withFixture { FrameFixture it ->
			Thread.sleep 60 * 1000
		}
	}

	TestFrameUtil createFrameWithList(def title) {
		def panel = new JPanel()
		def list = new JList([
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc",
			"Aaa",
			"Bbb",
			"Ccc"] as Object[])
		def menu = createMenu()
		def action = new PopupMenuList(list, menu)
		createFrame title, list
	}

	JPopupMenu createMenu() {
		def menu = new JPopupMenu()
		def item = new JMenuItem("Test")
		menu.add item
		menu
	}

	TestFrameUtil createFrame(String title, def list) {
		def panel = new JPanel(new BorderLayout())
		panel.add new JScrollPane(list)
		new TestFrameUtil(title, panel)
	}
}
