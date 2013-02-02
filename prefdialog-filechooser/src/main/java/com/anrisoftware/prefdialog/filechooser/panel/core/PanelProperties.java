package com.anrisoftware.prefdialog.filechooser.panel.core;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileView;

/**
 * Saves the file chooser panel properties that the user can change.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
class PanelProperties {

	private FileView view;

	PanelProperties() {
		this.view = FileView.SHORT;
	}

	public void setView(FileView view) {
		this.view = view;
	}

	public FileView getView() {
		return view;
	}

}
