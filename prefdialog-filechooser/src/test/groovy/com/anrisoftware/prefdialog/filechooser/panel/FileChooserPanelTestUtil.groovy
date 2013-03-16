package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.globalpom.utils.TestFrameUtil.*
import static com.anrisoftware.globalpom.utils.TestUtils.*
import static javax.swing.SwingUtilities.*
import static org.apache.commons.io.FileUtils.*

import java.awt.BorderLayout
import java.awt.Dimension

import javax.swing.BorderFactory
import javax.swing.JPanel

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelFactory
import com.anrisoftware.prefdialog.filechooser.panel.core.FileChooserPanelModule
import com.anrisoftware.prefdialog.filechooser.panel.imageresources.FileChooserImageResourcesModule
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Utilities to test the file chooser.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FileChooserPanelTestUtil {

	JPanel container

	FileChooserPanel panel

	public static File parent

	public static List dirs

	TestFrameUtil createFrame(String title) {
		container = new JPanel(new BorderLayout())
		container.setBorder BorderFactory.createEmptyBorder(2, 2, 2, 2)
		panel = factory.create(container).withCurrentDirectory(parent)
		invokeLater { createPanel(panel) }
		def frame = new TestFrameUtil(title, container)
		frame.frameSize = new Dimension(480, 360)
		frame
	}

	void createPanel(FileChooserPanel panel) {
		panel.createPanel()
	}

	public static List files

	public static Injector injector

	public static FileChooserPanelFactory factory

	public static void setupFactory() {
		injector = Guice.createInjector(
				new FileChooserPanelModule(),
				new FileChooserImageResourcesModule(),
				new ImagesResourcesModule(),
				new ResourcesImagesMapsModule(),
				new ResourcesSmoothScalingModule()
				)
		factory = injector.getInstance FileChooserPanelFactory
		parent = createFiles()
	}

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
