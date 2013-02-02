package com.anrisoftware.prefdialog.filechooser.panel.resources;

import java.util.Locale;

import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolAction;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolButtonsModel;
import com.anrisoftware.prefdialog.filechooser.panel.core.AbstractToolAction;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.ImageResource;
import com.anrisoftware.resources.images.api.Images;
import com.google.inject.assistedinject.Assisted;

public class ImageResources {

	private final FileChooserPanel fileChooser;

	private final Images images;

	private final Locale defaultLocale;

	@Inject
	ImageResources(@Assisted Images images,
			@Assisted FileChooserPanel fileChooser) {
		this.images = images;
		this.fileChooser = fileChooser;
		this.defaultLocale = UIManager.getDefaults().getDefaultLocale();
		setupOptionalToolButtons();
		setupOptionsButton();
	}

	private void setupOptionsButton() {
		AbstractToolAction toolAction = (AbstractToolAction) fileChooser
				.getOptionsButton().getAction();
		String resource = toolAction.getImageResource();
		if (resource != null) {
			IconSize size = IconSize.SMALL;
			toolAction.setLargeIcon(getIcon(resource, size));
		}
	}

	private void setupOptionalToolButtons() {
		ToolButtonsModel model = fileChooser.getToolButtonsModel();
		for (int i = 0; i < model.getSize(); i++) {
			ToolAction action = model.getActionAt(i);
			if (action instanceof AbstractToolAction) {
				AbstractToolAction toolAction = (AbstractToolAction) action;
				String resource = toolAction.getImageResource();
				if (resource != null) {
					IconSize size = IconSize.SMALL;
					toolAction.setLargeIcon(getIcon(resource, size));
				}
			}
		}
	}

	private ImageIcon getIcon(String name, IconSize size) {
		ImageResource resource = images.getResource(name, defaultLocale, size);
		return new ImageIcon(resource.getImage());
	}

}
