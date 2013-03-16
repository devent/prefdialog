package com.anrisoftware.prefdialog.filechooser.panel.imageresources;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.iconsize.IconSizeActionsModelImpl;
import com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.textposition.TextPositionActionsModelImpl;
import com.anrisoftware.resources.images.api.Images;
import com.google.inject.assistedinject.Assisted;

public class FileChooserImageResources {

	private final Images images;

	@Inject
	FileChooserImageResources(@Assisted Images images,
			@Assisted FileChooserPanel fileChooser) {
		this.images = images;
		setupTextPositionModel(fileChooser);
		setupIconSizeModel(fileChooser);
	}

	private void setupTextPositionModel(FileChooserPanel panel) {
		TextPositionActionsModelImpl model = (TextPositionActionsModelImpl) panel
				.getTextPositionActionsModel();
		model.setFileChooserPanel(panel);
	}

	private void setupIconSizeModel(FileChooserPanel panel) {
		IconSizeActionsModelImpl model = (IconSizeActionsModelImpl) panel
				.getIconSizeActionsModel();
		model.setFileChooserPanel(panel);
		model.setImages(images);
	}

}
