package com.anrisoftware.prefdialog.filechooser.panel.core.actions;

import java.awt.event.ActionListener;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;

abstract class AbstractOptionMenuAction implements ActionListener {

	protected FileModel fileModel;

	public void setFileModel(FileModel model) {
		this.fileModel = model;
	}
}
