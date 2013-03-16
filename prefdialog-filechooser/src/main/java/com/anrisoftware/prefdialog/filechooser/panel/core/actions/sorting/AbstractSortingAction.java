package com.anrisoftware.prefdialog.filechooser.panel.core.actions.sorting;

import java.awt.event.ActionListener;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;

/**
 * Saves the file chooser panel and the file model to change the sorting of
 * files.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
abstract class AbstractSortingAction implements ActionListener {

	/**
	 * The {@link FileChooserPanel}.
	 */
	protected FileChooserPanel fileChooserPanel;

	/**
	 * The {@link FileModel}.
	 */
	protected FileModel fileModel;

	/**
	 * Sets the file chooser panel for this action.
	 * 
	 * @param panel
	 *            the {@link FileChooserPanel}.
	 */
	public void setFileChooserPanel(FileChooserPanel panel) {
		this.fileChooserPanel = panel;
	}

	/**
	 * Sets the file model for this action.
	 * 
	 * @param model
	 *            the {@link FileModel}.
	 */
	public void setFileModel(FileModel model) {
		this.fileModel = model;
	}
}
