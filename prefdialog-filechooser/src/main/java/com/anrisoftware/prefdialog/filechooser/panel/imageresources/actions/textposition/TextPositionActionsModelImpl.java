package com.anrisoftware.prefdialog.filechooser.panel.imageresources.actions.textposition;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.Icon;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.TextPositionActionsModel;

public class TextPositionActionsModelImpl implements TextPositionActionsModel {

	private final TextOnlyAction textOnlyAction;

	private final IconOnlyAction iconOnlyAction;

	private final TextAlongsideIconAction textAlongsideIconAction;

	Map<String, Icon> savedIcons;

	Map<String, String> savedText;

	@Inject
	TextPositionActionsModelImpl(TextOnlyAction textOnlyAction,
			IconOnlyAction iconOnlyAction,
			TextAlongsideIconAction textAlongsideIconAction) {
		this.savedIcons = new HashMap<String, Icon>(100);
		this.savedText = new HashMap<String, String>(100);
		this.textOnlyAction = textOnlyAction;
		this.iconOnlyAction = iconOnlyAction;
		this.textAlongsideIconAction = textAlongsideIconAction;
		textOnlyAction.setModel(this);
		iconOnlyAction.setModel(this);
		textAlongsideIconAction.setModel(this);
	}

	public void setFileChooserPanel(FileChooserPanel panel) {
		textOnlyAction.setFileChooserPanel(panel);
		iconOnlyAction.setFileChooserPanel(panel);
		textAlongsideIconAction.setFileChooserPanel(panel);
	}

	@Override
	public ActionListener getTextOnlyAction() {
		return textOnlyAction;
	}

	@Override
	public ActionListener getIconOnlyAction() {
		return iconOnlyAction;
	}

	@Override
	public ActionListener getTextAlongsideIconAction() {
		return textAlongsideIconAction;
	}
}
