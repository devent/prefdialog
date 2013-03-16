package com.anrisoftware.prefdialog.filechooser.panel.imageresources;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.resources.images.api.Images;

public interface FileChooserImageResourcesFactory {

	FileChooserImageResources create(FileChooserPanel fileChooser, Images images);
}
