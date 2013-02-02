package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.globalpom.utils.TestFrameUtil.*
import static com.anrisoftware.globalpom.utils.TestUtils.*
import static org.apache.commons.io.FileUtils.*

import java.awt.BorderLayout
import java.awt.Dimension

import javax.swing.BorderFactory
import javax.swing.JPanel

import org.junit.AfterClass
import org.junit.BeforeClass

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelFactory
import com.anrisoftware.prefdialog.filechooser.panel.core.FileChooserPanelModule
import com.google.inject.Guice
import com.google.inject.Injector

class FileChooserPanelTestUtil {

	JPanel container

	FileChooserPanel panel

	public static File parent

	public static List dirs

	public static List files

	TestFrameUtil createFrame(String title) {
		container = new JPanel(new BorderLayout())
		container.setBorder BorderFactory.createEmptyBorder(2, 2, 2, 2)
		panel = factory.create(container).withCurrentDirectory(parent).createPanel()
		def frame = new TestFrameUtil(title, container)
		frame.frameSize = new Dimension(480, 360)
		frame
	}

	public static Injector injector

	public static FileChooserPanelFactory factory

	@BeforeClass
	public static void setupFactory() {
		injector = Guice.createInjector(new FileChooserPanelModule())
		factory = injector.getInstance FileChooserPanelFactory
		parent = createFiles()
	}

	@AfterClass
	public static void deleteFiles() {
		deleteDirectory parent
	}

	static createFiles() {
		parent = createTempDirectory()
		dirs = [
			new File(parent, "Aaa"),
			new File(parent, "Bbb"),
			new File(parent, "ccc")
		]
		dirs.each { it.mkdir() }

		files = [
			new File(parent, "aaa.txt"),
			new File(parent, "bbb.txt"),
			new File(parent, "ccc.txt"),
			new File(dirs[0], "Aaa-aaa.txt")
		]
		files.each { touch it }
		parent
	}
}
