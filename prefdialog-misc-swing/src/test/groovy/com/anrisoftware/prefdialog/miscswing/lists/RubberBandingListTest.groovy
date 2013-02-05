package com.anrisoftware.prefdialog.miscswing.lists

import java.awt.BorderLayout

import javax.swing.JPanel
import javax.swing.JScrollPane

import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

class RubberBandingListTest {

	@Test
	void "show list"() {
		String title = "RubberBandingListTest::show list"
		def panel = new JPanel()
		def list = new RubberBandingList([
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
		def frame = createFrame title, list
		frame.withFixture { Thread.sleep 60 * 1000 }
	}

	TestFrameUtil createFrame(String title, def list) {
		def panel = new JPanel(new BorderLayout())
		panel.add new JScrollPane(list)
		new TestFrameUtil(title, panel)
	}
}
