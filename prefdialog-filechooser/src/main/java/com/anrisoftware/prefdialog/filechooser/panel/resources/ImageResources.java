package com.anrisoftware.prefdialog.filechooser.panel.resources;

import java.util.Locale;

import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolAction;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolButtonsModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.AbstractToolAction;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;
import com.google.inject.assistedinject.Assisted;

public class ImageResources {

	private final FileChooserPanel fileChooser;

	private final Images images;

	@Inject
	ImageResources(@Assisted Images images,
			@Assisted FileChooserPanel fileChooser) {
		this.images = images;
		this.fileChooser = fileChooser;
		setupOptionalToolButtons();
	}

	private void setupOptionalToolButtons() {
		ToolButtonsModel model = fileChooser.getToolButtonsModel();
		for (int i = 0; i < model.getSize(); i++) {
			ToolAction action = model.getActionAt(i);
			if (action instanceof AbstractToolAction) {
				AbstractToolAction toolAction = (AbstractToolAction) action;
				String resource = toolAction.getImageResource();
				if (resource != null) {
					Locale locale = UIManager.getDefaults().getDefaultLocale();
					IconSize size = IconSize.SMALL;
					toolAction.setLargeIcon(new ImageIcon(images.getResource(
							resource, locale, size).getImage()));
				}
			}
		}
	}
}
