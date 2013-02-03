package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

public interface FileChooserPanel {

	static final String FILES_LIST_NAME = "files-list";

	static final String LOCATION_FIELD_NAME = "location-field";

	static final String PREVIEW_BUTTON_NAME = "preview-button";

	static final String REFRESH_BUTTON_NAME = "refresh-button";

	static final String UP_BUTTON_NAME = "up-button";

	static final String FORWARD_BUTTON_NAME = "forward-button";

	static final String BACK_BUTTON_NAME = "back-button";

	static final String CANCEL_BUTTON_NAME = "cancel-button";

	static final String FILTER_FIELD_NAME = "filter-field";

	static final String APPROVE_BUTTON_NAME = "approve-button";

	static final String NAME_FIELD_NAME = "name-field";

	static final String PLACES_LIST_NAME = "places-list";

	static final String PLACES_TOOLBAR_NAME = "places-toolbar";

	/**
	 * Creates the panel and returns it.
	 * 
	 * @return the {@link FileChooserPanel}.
	 */
	FileChooserPanel createPanel();

	/**
	 * Sets the current directory for the file chooser.
	 * 
	 * @param currentDirectory
	 *            the current {@link File} directory.
	 * 
	 * @return the {@link FileChooserPanel}.
	 */
	FileChooserPanel withCurrentDirectory(File currentDirectory);

	/**
	 * Sets the file system view for the file chooser.
	 * 
	 * @param view
	 *            the {@link FileSystemView} file system view.
	 * 
	 * @return the {@link FileChooserPanel}.
	 */
	FileChooserPanel withFileSystemView(FileSystemView view);

	/**
	 * Sets the file chooser properties.
	 * 
	 * @param view
	 *            the {@link FileChooserPanelProperties} properties.
	 * 
	 * @return the {@link FileChooserPanel}.
	 */
	FileChooserPanel withProperties(FileChooserPanelProperties properties);

	void approveAction();

	void cancelAction();

	JButton getApproveButton();

	JButton getCancelButton();

	AbstractButton getOptionsButton();

	JLabel getNameLabel();

	JLabel getFilterLabel();

	FileChooserPanelProperties getFileChooserPanelProperties();

	/**
	 * Sets the model to navigate in the directory structure.
	 * 
	 * @param stack
	 *            the {@link DirectoyModel}.
	 */
	void setDirectoryModel(DirectoyModel model);

	/**
	 * Returns the model to navigate in the directory structure.
	 * 
	 * @return the {@link DirectoyModel}.
	 */
	DirectoyModel getDirectoryModel();

	void setFilePropertiesModel(FilePropertiesModel model);

	FilePropertiesModel getFilePropertiesModel();

	void setPlacesModel(PlacesModel model);

	PlacesModel getPlacesModel();

	void setToolButtonsModel(ToolButtonsModel model);

	ToolButtonsModel getToolButtonsModel();

	void setFileSelectionModel(FileSelectionModel model);

	FileSelectionModel getFileSelectionModel();

	void setFileModel(FileModel model);

	FileModel getFileModel();

	@SuppressWarnings("rawtypes")
	void setFileViewRenderer(FileView view, FileViewRenderer renderer);

	void addActionListener(ActionListener l);

	void removeActionListener(ActionListener l);

	void updateUI();

}
