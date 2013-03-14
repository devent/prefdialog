package com.anrisoftware.prefdialog.filechooser.panel.core.actions.textposition;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.swing.Icon;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;

public class TextPositionActionsModel {

	private final TextOnlyAction textOnlyAction;

	private final IconOnlyAction iconOnlyAction;

	private final TextAlongsideIconAction textAlongsideIconAction;

	Map<String, Icon> savedIcons;

	Map<String, String> savedText;

	@Inject
	TextPositionActionsModel(TextOnlyAction textOnlyAction,
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

	public ActionListener getTextOnlyAction() {
		return textOnlyAction;
	}

	public ActionListener getIconOnlyAction() {
		return iconOnlyAction;
	}

	public ActionListener getTextAlongsideIconAction() {
		return textAlongsideIconAction;
	}
}
