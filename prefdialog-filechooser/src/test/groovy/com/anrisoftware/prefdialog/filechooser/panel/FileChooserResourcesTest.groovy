package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.*
import static org.apache.commons.io.FileUtils.*

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

class FileChooserResourcesTest extends FileChooserPanelTestUtil {

	@Test
	void "manually"() {
		def title = "FileChooserPanelTest::manually"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled true
		createImageResources("ReflectionsKDE4", panel)
		frame.withFixture({ Thread.sleep 60*1000 })
	}

	static ImagesFactory imagesFactory

	static ImageResourcesFactory imageResourcesFactory

	@BeforeClass
	static void setupFactory() {
		FileChooserPanelTestUtil.setupFactory()
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
