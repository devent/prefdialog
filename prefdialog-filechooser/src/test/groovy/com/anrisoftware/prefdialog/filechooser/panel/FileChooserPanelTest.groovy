package com.anrisoftware.prefdialog.filechooser.panel

import java.awt.BorderLayout

import javax.swing.BorderFactory
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
	void "show panel"() {
		def title = "FileChooserPanelTest::show panel"
		def container = new JPanel(new BorderLayout())
		container.setBorder BorderFactory.createEmptyBorder(2, 2, 2, 2)
		def panel = factory.create(container)
		def frame = new TestFrameUtil(title, container)
		TestUtils.endDelay = 20 * 1000
		frame.frameSize = container.preferredSize
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
