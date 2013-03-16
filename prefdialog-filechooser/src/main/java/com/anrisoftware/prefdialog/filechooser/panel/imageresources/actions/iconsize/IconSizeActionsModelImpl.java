package com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.iconsize;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.inject.Inject;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.IconSizeActionsModel;
import com.anrisoftware.resources.images.api.IconSize;
import com.anrisoftware.resources.images.api.Images;

public class IconSizeActionsModelImpl implements IconSizeActionsModel {

	private final ActionListener hugeSizeAction;

	private final ActionListener largeSizeAction;

	private final ActionListener mediumSizeAction;

	private final ActionListener smallSizeAction;

	private final ActionListener defaultSizeAction;

	private final ArrayList<AbstractIconSizeAction> sizeActions;

	@Inject
	IconSizeActionsModelImpl(HugeSizeAction hugeSizeAction,
			LargeSizeAction largeSizeAction, MediumSizeAction mediumSizeAction,
			SmallSizeAction smallSizeAction, DefaultSizeAction defaultSizeAction) {
		this.hugeSizeAction = hugeSizeAction;
		this.largeSizeAction = largeSizeAction;
		this.mediumSizeAction = mediumSizeAction;
		this.smallSizeAction = smallSizeAction;
		this.defaultSizeAction = defaultSizeAction;
		this.sizeActions = new ArrayList<AbstractIconSizeAction>();
		sizeActions.add(hugeSizeAction);
		sizeActions.add(largeSizeAction);
		sizeActions.add(mediumSizeAction);
		sizeActions.add(smallSizeAction);
		sizeActions.add(defaultSizeAction);
	}

	public void setImages(Images images) {
		for (AbstractIconSizeAction action : sizeActions) {
			action.setImages(images);
		}
	}

	public void setFileChooserPanel(FileChooserPanel panel) {
		for (AbstractIconSizeAction action : sizeActions) {
			action.setFileChooserPanel(panel);
		}
	}

	@Override
	public void setDefaultIconSize(IconSize size) {
		DefaultSizeAction action = (DefaultSizeAction) defaultSizeAction;
		action.setDefaultIconSize(size);
	}

	@Override
	public ActionListener getHugeSizeAction() {
		return hugeSizeAction;
	}

	@Override
	public ActionListener getLargeSizeAction() {
		return largeSizeAction;
	}

	@Override
	public ActionListener getMediumSizeAction() {
		return mediumSizeAction;
	}

	@Override
	public ActionListener getSmallSizeAction() {
		return smallSizeAction;
	}

	public ArrayList<AbstractIconSizeAction> getSizeActions() {
		return sizeActions;
	}

	@Override
	public ActionListener getDefaultSizeAction() {
		return defaultSizeAction;
	}
}
