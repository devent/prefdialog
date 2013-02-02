package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.*
import static org.apache.commons.io.FileUtils.*

import java.awt.BorderLayout
import java.awt.Dimension

import javax.swing.BorderFactory
import javax.swing.JFileChooser
import javax.swing.JPanel

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.globalpom.utils.TestFrameUtil
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelFactory
import com.anrisoftware.prefdialog.filechooser.panel.core.FileChooserPanelModule
import com.anrisoftware.prefdialog.filechooser.panel.resources.FileChooserResourcesModule
import com.anrisoftware.prefdialog.filechooser.panel.resources.ImageResources
import com.anrisoftware.prefdialog.filechooser.panel.resources.ImageResourcesFactory
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.google.inject.Guice
import com.google.inject.Injector

class FileChooserResourcesTest {

	JPanel container

	FileChooserPanel panel

	def dirs

	def files

	@Test
	void "show JFileChooser"() {
		def title = "FileChooserPanelTest::show JFileChooser"
		def chooser = new JFileChooser()
		def frame = new TestFrameUtil(title, new JPanel())
		frame.withFixture {
			chooser.showOpenDialog(frame.frame)
		}
	}

	@Test
	void "manually"() {
		def title = "FileChooserPanelTest::manually"
		withFiles "files", {
			def frame = createFrame(it, title)
			panel.getFileSelectionModel().setMultiSelectionEnabled true
			createImageResources("ReflectionsKDE4", panel)

			frame.frameSize = new Dimension(480, 360)
			frame.withFixture({ Thread.sleep 60*1000 })
		}, { createFiles(it) }
	}

	private TestFrameUtil createFrame(File currentDirectory, String title) {
		container = new JPanel(new BorderLayout())
		container.setBorder BorderFactory.createEmptyBorder(2, 2, 2, 2)
		panel = factory.create(container, currentDirectory)
		def frame = new TestFrameUtil(title, container)
		return frame
	}

	private createFiles(File parent) {
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
	}

	static Injector injector

	static FileChooserPanelFactory factory

	static ImagesFactory imagesFactory

	static ImageResourcesFactory imageResourcesFactory

	@BeforeClass
	static void setupFactory() {
		injector = Guice.createInjector(new FileChooserPanelModule(),
						new FileChooserResourcesModule(),
						new ImagesResourcesModule(), new ResourcesImagesMapsModule(),
						new ResourcesSmoothScalingModule())
		factory = injector.getInstance FileChooserPanelFactory
		imageResourcesFactory = injector.getInstance ImageResourcesFactory
		imagesFactory = injector.getInstance ImagesFactory
	}

	static ImageResources createImageResources(String baseName, def panel) {
		def images = imagesFactory.create(baseName)
		imageResourcesFactory.create(images, panel)
	}
}
