package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface FileChooserPanel {

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
