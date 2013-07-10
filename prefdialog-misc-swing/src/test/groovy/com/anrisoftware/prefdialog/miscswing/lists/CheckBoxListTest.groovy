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

import static com.anrisoftware.prefdialog.miscswing.lists.CheckListItem.*

import java.awt.BorderLayout

import javax.swing.JList
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.event.ChangeListener

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
/**
 * @see CheckBoxList
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class CheckBoxListTest {

	@Test
	void "show list"() {
		String title = "$NAME::show list"
		def selectedItems = []
		def items = createItems({ selectedItems << it.source } as ChangeListener)
		def list = new JList(items)
		CheckBoxList.decorate list

		def frame = createFrame title, list
		frame.withFixture { FrameFixture fix ->
			fix.list().selectItem(0)
			fix.list().selectItem(1)
			fix.list().selectItem(2)
		}

		assert selectedItems.contains(items[0])
		assert selectedItems.contains(items[1])
		assert selectedItems.contains(items[2])
		assert items[0].selected == false
		assert items[1].selected == false
		assert items[2].selected
	}

	//@Test
	void "manually"() {
		String title = "$NAME::manually"
		def items = createItems({ } as ChangeListener)
		def list = new JList(items)
		CheckBoxList.decorate list
		def frame = createFrame title, list
		frame.withFixture { FrameFixture fix ->
			Thread.sleep 60*1000
			assert false : "Deactivate manually test"
		}
	}

	TestFrameUtil createFrame(String title, def list) {
		def panel = new JPanel(new BorderLayout())
		panel.add new JScrollPane(list)
		new TestFrameUtil(title, panel)
	}

	static final String NAME = CheckBoxListTest.class.simpleName

	static CheckBoxListRenderer renderer = new CheckBoxListRenderer()

	static Object[] createItems(ChangeListener listener) {
		Object[] items = [
			asItem("Aaa"),
			asItem("Bbb"),
			asItem("Ccc")
		]
		items.each { it.addChangeListener listener }
		items
	}
}
