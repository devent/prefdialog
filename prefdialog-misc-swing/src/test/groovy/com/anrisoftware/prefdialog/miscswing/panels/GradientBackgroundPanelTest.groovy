package com.anrisoftware.prefdialog.miscswing.panels

import java.awt.BorderLayout
import java.awt.Dimension

import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

import net.miginfocom.swing.MigLayout

import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil

class GradientBackgroundPanelTest {

	@Test
	void "gray gradient panel"() {
		String title = "GradientBackgroundPanelTest::gray gradient panel"
		def panel = new JPanel()
		panel.setUI new GradientBackgroundPanelUi()
		def frame = createFrame title, panel
		frame.withFixture { Thread.sleep 60 * 1000 }
	}

	TestFrameUtil createFrame(String title, JPanel panel) {
		panel.setLayout new BorderLayout()
		def someStuff = new JPanel()
		someStuff.opaque = false
		someStuff.setLayout new MigLayout()
		someStuff.add new JLabel("Hello"), "wrap"
		someStuff.add new JButton("Hello"), "wrap"
		someStuff.add new JTextField("Hello"), "wrap"
		panel.add someStuff
		def frame = new TestFrameUtil(title, panel)
		frame.frameSize = new Dimension(480, 360)
		frame
	}
}
