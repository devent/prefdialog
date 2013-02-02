package com.anrisoftware.prefdialog.filechooser.panel.core;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.inject.Inject;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang3.event.EventListenerSupport;

import com.anrisoftware.prefdialog.filechooser.panel.api.DirectoyModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FilePropertiesModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileSelectionModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileView;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileViewRenderer;
import com.anrisoftware.prefdialog.filechooser.panel.api.PlacesModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolAction;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolButtonsModel;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultShortViewRenderer;
import com.google.inject.assistedinject.Assisted;

class FileChooserPanelImpl implements FileChooserPanel {

	private final EventListenerSupport<ActionListener> actionListeners;

	private final Container container;

	@SuppressWarnings("rawtypes")
	private final HashMap<FileView, FileViewRenderer> views;

	private FileSystemView systemView;

	private File currentDirectory;

	private UiFileChooserPanel panel;

	private FileModel fileModel;

	private FileSelectionModel selectionModel;

	private ToolButtonsModel toolButtonsModel;

	private PlacesModel placesModel;

	private FilePropertiesModel filePropertiesModel;

	private PanelProperties properties;

	private UiOptionsMenu optionsMenu;

	private NavigateDirectories navigateDirectories;

	private DirectoyModel directoryModel;

	private OptionsAction optionsAction;

	@SuppressWarnings("rawtypes")
	@Inject
	FileChooserPanelImpl(@Assisted Container container) {
		this.systemView = FileSystemView.getFileSystemView();
		this.currentDirectory = systemView.getDefaultDirectory();
		this.container = container;
		this.actionListeners = new EventListenerSupport<ActionListener>(
				ActionListener.class);
		this.views = new HashMap<FileView, FileViewRenderer>(
				FileView.values().length);
	}

	@Override
	public FileChooserPanel withCurrentDirectory(File currentDirectory) {
		this.currentDirectory = currentDirectory;
		return this;
	}

	@Override
	public FileChooserPanel withFileSystemView(FileSystemView view) {
		this.systemView = view;
		return this;
	}

	@Override
	public FileChooserPanel createPanel() {
		setup();
		return this;
	}

	private void setup() {
		directoryModel.setCurrentDirectory(currentDirectory);
		container.add(panel);
		fileModel.setFileSystemView(systemView);
		fileModel.setDirectory(currentDirectory);
		panel.setOptionsMenu(optionsMenu);
		panel.getOptionsButton().setAction(optionsAction);
		setupNativateDirectories();
		setupFilesList();
		setupToolButtons();

	}

	private void setupNativateDirectories() {
		navigateDirectories.setFileChooser(this);
		navigateDirectories.setFileModel(fileModel);
		navigateDirectories.setFileSystemView(systemView);
		navigateDirectories.setDirectoryModel(directoryModel);
		navigateDirectories.setList(panel.getFilesList());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setupFilesList() {
		FileViewRenderer renderer = views.get(properties.getView());
		JList list = panel.getFilesList();
		list.setModel(fileModel);
		list.setCellRenderer(renderer);
		list.setLayoutOrientation(renderer.getLayoutOrientation());
		list.setVisibleRowCount(renderer.getVisibleRowCount());
		list.setSelectionModel(selectionModel);
		list.putClientProperty("List.isFileList", Boolean.TRUE);
		selectionModel.setList(list);
	}

	private void setupToolButtons() {
		for (int i = 0; i < toolButtonsModel.getSize(); i++) {
			ToolAction action = toolButtonsModel.getActionAt(i);
			action.setFileModel(fileModel);
			action.setDirectoryModel(directoryModel);
			panel.addToolButton(action);
		}
	}

	@Override
	public void approveAction() {
		System.out.println("FileChooserPanelImpl.approveAction()"); // TODO
																	// println
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelAction() {
		// TODO Auto-generated method stub

	}

	@Inject
	void setNavigateDirectories(NavigateDirectories navigateDirectories) {
		this.navigateDirectories = navigateDirectories;
	}

	@Inject
	void setPanelProperties(PanelProperties properties) {
		this.properties = properties;
	}

	@Inject
	void setUiOptionsMenu(UiOptionsMenu optionsMenu) {
		this.optionsMenu = optionsMenu;
	}

	@Inject
	void setUiPanel(UiFileChooserPanel panel) {
		this.panel = panel;
	}

	@Inject
	void setShortViewRenderer(DefaultShortViewRenderer renderer) {
		setFileViewRenderer(FileView.SHORT, renderer);
	}

	@Inject
	void setOptionsAction(OptionsAction action) {
		this.optionsAction = action;
	}

	@Override
	public JButton getApproveButton() {
		return panel.getApproveButton();
	}

	@Override
	public JButton getCancelButton() {
		return panel.getCancelButton();
	}

	@Override
	public AbstractButton getOptionsButton() {
		return panel.optionsButton;
	}

	@Override
	public JLabel getNameLabel() {
		return panel.getNameLabel();
	}

	@Override
	public JLabel getFilterLabel() {
		return panel.getFilterLabel();
	}

	@Inject
	@Override
	public void setDirectoryModel(DirectoyModel model) {
		this.directoryModel = model;
	}

	@Override
	public DirectoyModel getDirectoryModel() {
		return directoryModel;
	}

	// @Inject
	@Override
	public void setFilePropertiesModel(FilePropertiesModel model) {
		this.filePropertiesModel = model;
	}

	@Override
	public FilePropertiesModel getFilePropertiesModel() {
		return filePropertiesModel;
	}

	// @Inject
	@Override
	public void setPlacesModel(PlacesModel model) {
		this.placesModel = model;
	}

	@Override
	public PlacesModel getPlacesModel() {
		return placesModel;
	}

	@Inject
	@Override
	public void setToolButtonsModel(ToolButtonsModel model) {
		this.toolButtonsModel = model;
		// panel.removeToolButtons();
	}

	@Override
	public ToolButtonsModel getToolButtonsModel() {
		return toolButtonsModel;
	}

	@Inject
	@Override
	public void setFileSelectionModel(FileSelectionModel model) {
		this.selectionModel = model;
	}

	@Override
	public FileSelectionModel getFileSelectionModel() {
		return selectionModel;
	}

	@Inject
	@Override
	public void setFileModel(FileModel fileModel) {
		this.fileModel = fileModel;
	}

	@Override
	public FileModel getFileModel() {
		return fileModel;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setFileViewRenderer(FileView view, FileViewRenderer renderer) {
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
