package com.anrisoftware.prefdialog.filechooser.panel

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelFactory
import com.anrisoftware.prefdialog.filechooser.panel.core.FileChooserPanelModule
import com.anrisoftware.prefdialog.filechooser.panel.resources.FileChooserImageResources
import com.anrisoftware.prefdialog.filechooser.panel.resources.FileChooserImageResourcesFactory
import com.anrisoftware.prefdialog.filechooser.panel.resources.FileChooserImageResourcesModule
import com.anrisoftware.resources.images.api.ImagesFactory
import com.anrisoftware.resources.images.images.ImagesResourcesModule
import com.anrisoftware.resources.images.maps.ResourcesImagesMapsModule
import com.anrisoftware.resources.images.scaling.ResourcesSmoothScalingModule
import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Create file chooser panel with image resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FileChooserImageResourcesTestUtils {

	public static Injector injector

	public static FileChooserPanelFactory factory

	static ImagesFactory imagesFactory

	static FileChooserImageResourcesFactory imageResourcesFactory

	static void setupFactory() {
		FileChooserPanelTestUtil.setupFactory()
		injector = Guice.createInjector(new FileChooserPanelModule(),
				new FileChooserImageResourcesModule(),
				new ImagesResourcesModule(), new ResourcesImagesMapsModule(),
				new ResourcesSmoothScalingModule())
		factory = injector.getInstance FileChooserPanelFactory
		imageResourcesFactory = injector.getInstance FileChooserImageResourcesFactory
		imagesFactory = injector.getInstance ImagesFactory
	}

	static FileChooserImageResources createImageResources(String baseName, def panel) {
		def images = imagesFactory.create(baseName)
		imageResourcesFactory.create panel, images
	}
}
