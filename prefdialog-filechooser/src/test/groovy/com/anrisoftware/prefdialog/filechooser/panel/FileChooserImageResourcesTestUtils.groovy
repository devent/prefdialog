package com.anrisoftware.prefdialog.filechooser.panel

import com.anrisoftware.prefdialog.filechooser.panel.imageresources.FileChooserImageResources
import com.anrisoftware.prefdialog.filechooser.panel.imageresources.FileChooserImageResourcesFactory
import com.anrisoftware.resources.images.api.ImagesFactory

/**
 * Create file chooser panel with image resources.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class FileChooserImageResourcesTestUtils {

	static ImagesFactory imagesFactory

	static FileChooserImageResourcesFactory imageResourcesFactory

	static void setupFactory() {
		FileChooserPanelTestUtil.setupFactory()
		imageResourcesFactory = FileChooserPanelTestUtil.injector.getInstance(
				FileChooserImageResourcesFactory)
		imagesFactory = FileChooserPanelTestUtil.injector.getInstance(
				ImagesFactory)
	}

	static FileChooserImageResources createImageResources(String baseName, def panel) {
		def images = imagesFactory.create(baseName)
		imageResourcesFactory.create panel, images
	}
}
