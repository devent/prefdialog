package com.anrisoftware.prefdialog.filechooser.panel.core;

import static java.lang.System.getProperty;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang3.event.EventListenerSupport;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FilePropertiesModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileSelectionModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.PlacesModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolButtonsModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultFileModel;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

class FileChooserPanelImpl implements FileChooserPanel {

	private final UiFileChooserPanel panel;

	private final EventListenerSupport<ActionListener> actionListeners;

	private FileModel fileModel;

	private FileSelectionModel selectionModel;

	private ToolButtonsModel toolButtonsModel;

	private PlacesModel placesModel;

	private FilePropertiesModel filePropertiesModel;

	@AssistedInject
	FileChooserPanelImpl(UiFileChooserPanel panel, DefaultFileModel fileModel) {
		this(panel, fileModel, new File(getProperty("user.home")));
	}

	@AssistedInject
	FileChooserPanelImpl(UiFileChooserPanel panel, DefaultFileModel fileModel,
			@Assisted String currentDirectory) {
		this(panel, fileModel, new File(currentDirectory));
	}

	@AssistedInject
	FileChooserPanelImpl(UiFileChooserPanel panel, DefaultFileModel fileModel,
			@Assisted File currentDirectory) {
		this(panel, fileModel, currentDirectory, FileSystemView
				.getFileSystemView());
	}

	@AssistedInject
	FileChooserPanelImpl(UiFileChooserPanel panel, DefaultFileModel fileModel,
			@Assisted String currentDirectory, @Assisted FileSystemView view) {
		this(panel, fileModel, new File(currentDirectory), view);
	}

	@AssistedInject
	FileChooserPanelImpl(UiFileChooserPanel panel, DefaultFileModel fileModel,
			@Assisted File currentDirectory, @Assisted FileSystemView view) {
		this.actionListeners = new EventListenerSupport<ActionListener>(
				ActionListener.class);
		this.panel = panel;
		this.fileModel = fileModel;
		fileModel.setFileSystemView(view);
	}

	@Override
	public void approveAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setApproveButton(JButton button) {
		panel.setApproveButton(button);
	}

	@Override
	public JButton getApproveButton() {
		return panel.getApproveButton();
	}

	@Override
	public void setCancelButton(JButton button) {
		panel.setCancelButton(button);
	}

	@Override
	public JButton getCancelButton() {
		return panel.getCancelButton();
	}

	@Override
	public void setNameLabel(JLabel label) {
		panel.setNameLabel(label);
	}

	@Override
	public JLabel getNameLabel() {
		return panel.getNameLabel();
	}

	@Override
	public void setFilterLabel(JLabel label) {
		panel.setFilterLabel(label);
	}

	@Override
	public JLabel getFilterLabel() {
		return panel.getFilterLabel();
	}

	@Override
	public void setFilePropertiesModel(FilePropertiesModel model) {
		this.filePropertiesModel = model;
	}

	@Override
	public FilePropertiesModel getFilePropertiesModel() {
		return filePropertiesModel;
	}

	@Override
	public void setPlacesModel(PlacesModel model) {
		this.placesModel = model;
	}

	@Override
	public PlacesModel getPlacesModel() {
		return placesModel;
	}

	@Override
	public void setToolButtonsModel(ToolButtonsModel model) {
		this.toolButtonsModel = model;
	}

	@Override
	public ToolButtonsModel getToolButtonsModel() {
		return toolButtonsModel;
	}

	@Override
	public void setFileSelectionModel(FileSelectionModel model) {
		this.selectionModel = model;
	}

	@Override
	public FileSelectionModel getFileSelectionModel() {
		return selectionModel;
	}

	@Override
	public void setFileModel(FileModel fileModel) {
		this.fileModel = fileModel;
	}

	@Override
	public FileModel getFileModel() {
		return fileModel;
	}

	@Override
	public void addActionListener(ActionListener l) {
		actionListeners.addListener(l);
	}

	@Override
	public void removeActionListener(ActionListener l) {
		actionListeners.removeListener(l);
	}

	@Override
	public void updateUI() {
		panel.updateUI();
	}
}
