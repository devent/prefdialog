package com.anrisoftware.prefdialog.filechooser.panel

import javax.swing.JPanel

import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

class FileChooserPanelTest {

	@Test
	void "show panel"() {
		def title = "FileChooserPanelTest::show panel"
		def panel = new JPanel()
		new TestFrameUtil(title, panel).withFixture { }
	}
}
