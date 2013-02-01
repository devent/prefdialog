package com.anrisoftware.prefdialog.filechooser.panel

import java.awt.BorderLayout
import java.awt.Dimension

import javax.swing.BorderFactory
import javax.swing.JFileChooser
import javax.swing.JPanel

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.globalpom.utils.TestUtils
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelFactory
import com.anrisoftware.prefdialog.filechooser.panel.core.FileChooserPanelModule
import com.google.inject.Guice
import com.google.inject.Injector

class FileChooserPanelTest {

	@Test
	void "show JFileChooser"() {
		def title = "FileChooserPanelTest::show JFileChooser"
		def chooser = new JFileChooser()
		def frame = new TestFrameUtil(title, new JPanel())
		TestUtils.endDelay = 20 * 1000
		frame.withFixture {
			chooser.showOpenDialog(frame.frame)
		}
	}

	@Test
	void "show panel"() {
		def title = "FileChooserPanelTest::show panel"
		def container = new JPanel(new BorderLayout())
		container.setBorder BorderFactory.createEmptyBorder(2, 2, 2, 2)
		def panel = factory.create(container)
		def frame = new TestFrameUtil(title, container)
		TestUtils.endDelay = 20 * 1000
		frame.frameSize = new Dimension(480, 360)
		frame.withFixture { }
	}

	static Injector injector

	static FileChooserPanelFactory factory

	@BeforeClass
	static void setupFactory() {
		injector = Guice.createInjector(new FileChooserPanelModule())
		factory = injector.getInstance FileChooserPanelFactory
	}
}