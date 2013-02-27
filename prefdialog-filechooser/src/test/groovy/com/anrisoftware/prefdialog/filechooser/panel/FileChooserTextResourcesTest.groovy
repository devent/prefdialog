package com.anrisoftware.prefdialog.filechooser.panel

import static com.anrisoftware.globalpom.utils.TestUtils.*
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel.*
import static org.apache.commons.io.FileUtils.*

import org.junit.BeforeClass
import org.junit.Test

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelFactory
import com.anrisoftware.prefdialog.filechooser.panel.core.FileChooserPanelModule
import com.anrisoftware.prefdialog.filechooser.panel.textresources.FileChooserTextResources
import com.anrisoftware.prefdialog.filechooser.panel.textresources.FileChooserTextResourcesFactory
import com.anrisoftware.prefdialog.filechooser.panel.textresources.FileChooserTextResourcesModule
import com.anrisoftware.resources.binary.binaries.BinariesResourcesModule
import com.anrisoftware.resources.binary.maps.BinariesDefaultMapsModule
import com.anrisoftware.resources.texts.central.TextsResources
import com.anrisoftware.resources.texts.maps.TextsDefaultMapsModule
import com.anrisoftware.resources.texts.texts.TextsResourcesCharsetModule
import com.anrisoftware.resources.texts.texts.TextsResourcesModule
import com.google.inject.Guice
import com.google.inject.Injector

class FileChooserTextResourcesTest extends FileChooserPanelTestUtil {

	@Test
	void "manually"() {
		def title = "FileChooserTextResourcesTest::manually"
		def frame = createFrame(title)
		panel.getFileSelectionModel().setMultiSelectionEnabled true
		createFileChooserTextResources(panel)
		frame.withFixture({ Thread.sleep 60*1000 })
	}

	static FileChooserTextResourcesFactory textResourcesFactory

	static TextsResources textsResources

	@BeforeClass
	static void setupFactory() {
		FileChooserPanelTestUtil.setupFactory()
		injector = Guice.createInjector(new FileChooserPanelModule(),
						new FileChooserTextResourcesModule(),
						new TextsResourcesModule(), new TextsDefaultMapsModule(),
						new TextsResourcesCharsetModule(),
						new BinariesResourcesModule(),
						new BinariesDefaultMapsModule())
		factory = injector.getInstance FileChooserPanelFactory
		textResourcesFactory = injector.getInstance FileChooserTextResourcesFactory
		textsResources = injector.getInstance TextsResources
	}

	static FileChooserTextResources createFileChooserTextResources(def panel) {
		textResourcesFactory.create panel, textsResources
	}
}
