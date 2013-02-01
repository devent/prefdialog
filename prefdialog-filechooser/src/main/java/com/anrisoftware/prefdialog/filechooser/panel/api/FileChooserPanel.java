package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

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

	void approveAction();

	void cancelAction();

	JButton getApproveButton();

	JButton getCancelButton();

	JLabel getNameLabel();

	JLabel getFilterLabel();

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
	void setFileView(FileView view, FileViewRenderer renderer);

	void addActionListener(ActionListener l);

	void removeActionListener(ActionListener l);

	void updateUI();

}
