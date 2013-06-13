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
