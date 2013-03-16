package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.*
import static org.apache.commons.io.FileUtils.*

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelFactory
import com.anrisoftware.prefdialog.filechooser.panel.core.FileChooserPanelModule
import com.anrisoftware.prefdialog.filechooser.panel.imageresources.FileChooserImageResourcesFactory
import com.anrisoftware.prefdialog.filechooser.panel.imageresources.FileChooserImageResourcesModule
import com.anrisoftware.prefdialog.filechooser.panel.textresources.FileChooserTextResourcesFactory
import com.anrisoftware.prefdialog.filechooser.panel.textresources.FileChooserTextResourcesModule
import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.anrisoftware.resources.texts.central.TextsResources
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule
import com.anrisoftware.resources.texts.texts.TextsResourcesCharsetModule
import com.anrisoftware.resources.texts.texts.TextsResourcesModule
import com.google.inject.Guice
import com.google.inject.Injector

class FileChooserResourcesTest extends FileChooserPanelTestUtil {

	@Test
	void "manually"() {
		def title = "FileChooserResourcesTest::manually"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled true
		createFileChooserTextResources panel, "ReflectionsKDE4"
		frame.withFixture({ Thread.sleep 60*1000 })
	}

	static FileChooserTextResourcesFactory textResourcesFactory

	static TextsResources textsResources

	static ImagesFactory imagesFactory

	static FileChooserImageResourcesFactory imageResourcesFactory

	@BeforeClass
	static void setupFactory() {
		FileChooserPanelTestUtil.setupFactory()
		injector = Guice.createInjector(new FileChooserPanelModule(),
				new FileChooserTextResourcesModule(),
				new FileChooserImageResourcesModule(),
				new TextsResourcesModule(),
				new TextsDefaultMapsModule(),
				new TextsResourcesCharsetModule(),
				new BinariesResourcesModule(),
				new BinariesDefaultMapsModule(),
				new ImagesResourcesModule(),
				new ResourcesImagesMapsModule(),
				new ResourcesSmoothScalingModule())
		factory = injector.getInstance FileChooserPanelFactory
		textResourcesFactory = injector.getInstance FileChooserTextResourcesFactory
		textsResources = injector.getInstance TextsResources
		imageResourcesFactory = injector.getInstance FileChooserImageResourcesFactory
		imagesFactory = injector.getInstance ImagesFactory
	}

	static void createFileChooserTextResources(def panel, String baseName) {
		textResourcesFactory.create panel, textsResources
		def images = imagesFactory.create(baseName)
		imageResourcesFactory.create panel, images
	}
}
