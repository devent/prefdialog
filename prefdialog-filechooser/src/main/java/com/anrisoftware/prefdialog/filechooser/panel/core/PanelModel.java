package com.anrisoftware.prefdialog.filechooser.panel.core;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileView;

class PanelModel {

	private FileView view;

	PanelModel() {
		this.view = FileView.SHORT;
	}

	public void setView(FileView view) {
		this.view = view;
	}

	public FileView getView() {
		return view;
	}

}
