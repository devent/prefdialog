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
