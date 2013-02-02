package com.anrisoftware.prefdialog.filechooser.panel.resources;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.resources.images.api.Images;

public interface ImageResourcesFactory {

	ImageResources create(Images images, FileChooserPanel fileChooser);
}
