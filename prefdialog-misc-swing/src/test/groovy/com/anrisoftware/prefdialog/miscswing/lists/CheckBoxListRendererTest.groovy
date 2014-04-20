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
package com.anrisoftware.prefdialog.miscswing.lists

import java.awt.BorderLayout

import javax.swing.JList
import javax.swing.JPanel
import javax.swing.JScrollPane

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * @see CheckBoxListRenderer
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class CheckBoxListRendererTest {

	@Test
	void "show list"() {
		String title = "$NAME::show list"
		def list = new JList(items)
		list.setCellRenderer renderer
		def frame = createFrame title, list
		frame.withFixture { FrameFixture fix ->
			fix.list().selectItem(0)
			fix.list().selectItem(1)
			fix.list().selectItem(2)
		}
	}

	TestFrameUtil createFrame(String title, def list) {
		def panel = new JPanel(new BorderLayout())
		panel.add new JScrollPane(list)
		new TestFrameUtil(title, panel)
	}

	static final String NAME = CheckBoxListRendererTest.class.simpleName

	static items = ["Aaa", "Bbb", "Ccc"] as Object[]

	static CheckBoxListRenderer renderer = new CheckBoxListRenderer()
}
