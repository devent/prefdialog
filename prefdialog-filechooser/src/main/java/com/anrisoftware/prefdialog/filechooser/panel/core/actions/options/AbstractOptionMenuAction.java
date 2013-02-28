package com.anrisoftware.prefdialog.filechooser.panel.core.actions.options;

import java.awt.event.ActionListener;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;

public abstract class AbstractOptionMenuAction implements ActionListener {

	protected FileChooserPanel fileChooserPanel;

	protected FileModel fileModel;

	public void setFileChooserPanel(FileChooserPanel panel) {
		this.fileChooserPanel = panel;
	}

	public void setFileModel(FileModel model) {
		this.fileModel = model;
	}
}
