package com.anrisoftware.prefdialog.filechooser.panel.textresources;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.resources.texts.central.TextsResources;

public interface FileChooserTextResourcesFactory {

	FileChooserTextResources create(FileChooserPanel fileChooser,
			TextsResources texts);
}
