package com.anrisoftware.prefdialog.miscswing.lists

import java.awt.BorderLayout
import java.awt.event.ActionListener
import java.awt.event.KeyEvent

import javax.swing.JList
import javax.swing.JPanel
import javax.swing.JScrollPane

import org.fest.swing.fixture.FrameFixture
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

/**
 * @see ActionList
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class ActionListTest {

	@Test
	void "select with mouse"() {
		String title = "$NAME::select with mouse"
		String lastActionCommand = null
		int actionCount = 0
		String command = "action"
		def frame = createFrameWithList title, command, { evt ->
			lastActionCommand = evt.actionCommand
			actionCount++
		}
		frame.withFixture { FrameFixture it ->
			it.list().clickItem(0)
			it.list().clickItem(1)
			it.list().clickItem(2)
		}
		assert lastActionCommand == command
		assert actionCount == 6
	}

	@Test
	void "select same value with mouse"() {
		String title = "$NAME::select same value with mouse"
		String lastActionCommand = null
		int actionCount = 0
		String command = "action"
		def frame = createFrameWithList title, command, { evt ->
			lastActionCommand = evt.actionCommand
			actionCount++
		}
		frame.withFixture { FrameFixture it ->
			it.list().clickItem(0)
			it.list().clickItem(0)
			it.list().clickItem(0)
		}
		assert lastActionCommand == command
		assert actionCount == 4
	}

	@Test
	void "select with mouse and use spacebar"() {
		String title = "$NAME::select with mouse and use spacebar"
		String lastActionCommand = null
		int actionCount = 0
		String command = "action"
		def frame = createFrameWithList title, command, { evt ->
			lastActionCommand = evt.actionCommand
			actionCount++
		}
		frame.withFixture { FrameFixture it ->
			it.list().selectItem(0)
			it.list().pressAndReleaseKeys(KeyEvent.VK_SPACE)
		}
		assert lastActionCommand == command
		assert actionCount == 3
	}

	@Test
	void "select with mouse and use enter"() {
		String title = "$NAME::select with mouse and use enter"
		String lastActionCommand = null
		int actionCount = 0
		String command = "action"
		def frame = createFrameWithList title, command, { evt ->
			lastActionCommand = evt.actionCommand
			actionCount++
		}
		frame.withFixture { FrameFixture it ->
			it.list().selectItem(0)
			it.list().pressAndReleaseKeys(KeyEvent.VK_ENTER)
		}
		assert lastActionCommand == command
		assert actionCount == 3
	}

	TestFrameUtil createFrameWithList(def title, def command, def actionCallback) {
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
		def action = new ActionList(list)
		action.setActionCommand command
		action.addActionListener({ evt -> actionCallback(evt) } as ActionListener)
		createFrame title, list
	}

	TestFrameUtil createFrame(String title, def list) {
		def panel = new JPanel(new BorderLayout())
		panel.add new JScrollPane(list)
		new TestFrameUtil(title, panel)
	}

	static final String NAME = ActionListTest.class.simpleName
}
