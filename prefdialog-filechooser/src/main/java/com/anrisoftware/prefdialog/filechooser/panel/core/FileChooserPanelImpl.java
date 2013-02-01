package com.anrisoftware.prefdialog.filechooser.panel.core;

import static java.lang.System.getProperty;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang3.event.EventListenerSupport;

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FilePropertiesModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileSelectionModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileView;
import com.anrisoftware.prefdialog.filechooser.panel.api.PlacesModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolButtonsModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultFileModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultShortView;
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

	private final Container container;

	@SuppressWarnings("rawtypes")
	private final HashMap<FileView, ListCellRenderer> views;

	private final PanelModel model;

	@AssistedInject
	FileChooserPanelImpl(UiFileChooserPanel panel, PanelModel model,
			DefaultFileModel fileModel, DefaultShortView shortView,
			@Assisted Container container) {
		this(panel, model, fileModel, shortView, container, new File(
				getProperty("user.home")));
	}

	@AssistedInject
	FileChooserPanelImpl(UiFileChooserPanel panel, PanelModel model,
			DefaultFileModel fileModel, DefaultShortView shortView,
			@Assisted Container container, @Assisted String currentDirectory) {
		this(panel, model, fileModel, shortView, container, new File(
				currentDirectory));
	}

	@AssistedInject
	FileChooserPanelImpl(UiFileChooserPanel panel, PanelModel model,
			DefaultFileModel fileModel, DefaultShortView shortView,
			@Assisted Container container, @Assisted File currentDirectory) {
		this(panel, model, fileModel, shortView, container, currentDirectory,
				FileSystemView.getFileSystemView());
	}

	@AssistedInject
	FileChooserPanelImpl(UiFileChooserPanel panel, PanelModel model,
			DefaultFileModel fileModel, DefaultShortView shortView,
			@Assisted Container container, @Assisted String currentDirectory,
			@Assisted FileSystemView view) {
		this(panel, model, fileModel, shortView, container, new File(
				currentDirectory), view);
	}

	@SuppressWarnings("rawtypes")
	@AssistedInject
	FileChooserPanelImpl(UiFileChooserPanel panel, PanelModel model,
			DefaultFileModel fileModel, DefaultShortView shortView,
			@Assisted Container container, @Assisted File currentDirectory,
			@Assisted FileSystemView view) {
		this.actionListeners = new EventListenerSupport<ActionListener>(
				ActionListener.class);
		this.panel = panel;
		this.model = model;
		this.fileModel = fileModel;
		this.container = container;
		this.views = new HashMap<FileView, ListCellRenderer>(
				FileView.values().length);
		setFileView(FileView.SHORT, shortView);
		setup(view, currentDirectory);
	}

	private void setup(FileSystemView view, File currentDirectory) {
		container.add(panel);
		fileModel.setFileSystemView(view);
		fileModel.setDirectory(currentDirectory);
		setupFilesList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setupFilesList() {
		JList list = panel.getFilesList();
		list.setModel(fileModel);
		list.setCellRenderer(views.get(model.getView()));
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
	public void setFileView(FileView view,
			@SuppressWarnings("rawtypes") ListCellRenderer renderer) {
		views.put(view, renderer);
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
