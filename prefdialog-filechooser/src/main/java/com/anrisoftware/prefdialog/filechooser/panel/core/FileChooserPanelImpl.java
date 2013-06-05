package com.anrisoftware.prefdialog.filechooser.panel.core;

import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelProperties.DEFAULT_ICON_SIZE_PROPERTY;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelProperties.ICON_SIZE_PROPERTY;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelProperties.SELECTED_FILES_IN_QUEUE_PROPERTY;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelProperties.TEXT_POSITION_PROPERTY;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileModel.DIRECTORY_PROPERTY;
import static com.anrisoftware.prefdialog.filechooser.panel.api.FileModel.FILE_SORT_PROPERTY;
import static java.util.Collections.unmodifiableMap;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.lang3.event.EventListenerSupport;

import com.anrisoftware.prefdialog.annotations.TextPosition;
import com.anrisoftware.prefdialog.filechooser.panel.api.DirectoyModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelProperties;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileNameEditor;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileNameRenderer;
import com.anrisoftware.prefdialog.filechooser.panel.api.FilePropertiesModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileSelectionModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileSort;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileView;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileViewRenderer;
import com.anrisoftware.prefdialog.filechooser.panel.api.IconSizeActionsModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.LocationsModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.PlacesModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.PlacesRenderer;
import com.anrisoftware.prefdialog.filechooser.panel.api.TextPositionActionsModel;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolAction;
import com.anrisoftware.prefdialog.filechooser.panel.api.ToolButtonsModel;
import com.anrisoftware.prefdialog.filechooser.panel.core.actions.sorting.SortActionsModel;
import com.anrisoftware.prefdialog.filechooser.panel.core.docking.Docking;
import com.anrisoftware.prefdialog.filechooser.panel.core.docking.DockingFactory;
import com.anrisoftware.prefdialog.filechooser.panel.defaults.DefaultShortViewRenderer;
import com.anrisoftware.prefdialog.miscswing.lists.ActionList;
import com.anrisoftware.resources.images.api.IconSize;
import com.google.inject.assistedinject.Assisted;

class FileChooserPanelImpl implements FileChooserPanel {

	private final EventListenerSupport<ActionListener> actionListeners;

	private final Container container;

	@SuppressWarnings("rawtypes")
	private final HashMap<FileView, FileViewRenderer> views;

	private final ListSelectionListener fileSelectionListener;

	private FileSystemView systemView;

	private File currentDirectory;

	private UiFilesListPanel panel;

	private FileModel fileModel;

	private FileSelectionModel selectionModel;

	private ToolButtonsModel toolButtonsModel;

	private PlacesModel placesModel;

	private FilePropertiesModel filePropertiesModel;

	private FileChooserPanelProperties properties;

	private UiOptionsMenu optionsMenu;

	private NavigateDirectories navigateDirectories;

	private DirectoyModel directoryModel;

	private OptionsAction optionsAction;

	private SelectedFilesQueueModel selectedFilesQueueModel;

	private PropertyChangeListener selectedFilesInQueueListener;

	private FileNameRenderer fileNameRenderer;

	private FileNameEditor fileNameEditor;

	private PropertyChangeListener directoryListener;

	private ActionListener fileNameListener;

	protected AdjustingSemaphore nameFieldAdjusting;

	private SortActionsModel sortActionsModel;

	private PropertyChangeListener fileSortListener;

	private DockingFactory dockingFactory;

	private Docking docking;

	private UiPlacesPanel placesPanel;

	private UiInputPanel inputPanel;

	private ActionListener placesListener;

	private ActionList<File> placesActionList;

	private PlacesRenderer placesRenderer;

	private TextPositionActionsModel textPositionActionsModel;

	private PropertyChangeListener textPositionListener;

	private LocationsModel locationsModel;

	private ActionListener locationsListener;

	private IconSizeActionsModel iconSizeActionsModel;

	private PropertyChangeListener iconSizeListener;

	private PropertyChangeListener defaultIconSizeListener;

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
		this.nameFieldAdjusting = new AdjustingSemaphore();
		this.fileSelectionListener = new AdjustingAwareListSelectionListener() {

			@Override
			protected void doValueChanged(ListSelectionEvent event) {
				List<File> files = selectionModel.getSelectedFileList();
				nameFieldAdjusting.adjusting();
				if (files.size() == 0) {
					properties.clearSelectedFiles();
					inputPanel.nameField.setSelectedItem(new HashSet<File>());
				} else {
					properties.addSelectedFiles(files);
					inputPanel.nameField.setSelectedItem(new HashSet<File>(
							files));
				}
				nameFieldAdjusting.doneAdjusting();
			}
		};
		this.selectedFilesInQueueListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				selectedFilesQueueModel.setSelectedFiles(properties
						.getSelectedFilesInQueue());
			}
		};
		this.directoryListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				File file = fileModel.getDirectory();
				fileNameEditor.setCurrentDirectory(file);
				directoryModel.setCurrentDirectory(file);
				locationsModel.addLocation(file);
				locationsModel.setSelectedItem(file);
				int index = placesModel.indexOf(file);
				if (index != -1) {
					placesPanel.placesList.setSelectedIndex(index);
				} else {
					placesPanel.placesList.clearSelection();
				}
			}
		};
		this.fileNameListener = new AdjustingAwareActionListener(
				nameFieldAdjusting) {

			@Override
			protected void doAction(ActionEvent event) {
				@SuppressWarnings("unchecked")
				Set<File> files = (Set<File>) inputPanel.nameField
						.getSelectedItem();
				if (files == null) {
					return;
				}
				selectionModel.clearSelection();
				selectionModel.setSelectedFiles(files);
				Iterator<File> it = files.iterator();
				if (!it.hasNext()) {
					return;
				}
				File file = it.next();
				if (file.isDirectory()) {
					fileModel.setDirectory(file);
				} else {
					fileModel.setDirectory(file.getParentFile());
				}
			}
		};
		this.fileSortListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				updateSelectedFileSort((FileSort) evt.getNewValue());
			}

		};
		this.placesListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				File file = placesPanel.placesList.getSelectedValue();
				selectionModel.clearSelection();
				fileModel.setDirectory(file);
			}
		};
		this.textPositionListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				optionsMenu.updateSelectedTextPosition((TextPosition) evt
						.getNewValue());
			}
		};
		this.locationsListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Object selected = locationsModel.getSelectedItem();
				File path = new File(selected.toString());
				fileModel.setDirectory(path);
				directoryModel.setCurrentDirectory(path);
			}
		};
		this.iconSizeListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				IconSize size = (IconSize) evt.getNewValue();
				optionsMenu.updateSelectedIconSize(size);
			}
		};
		this.defaultIconSizeListener = new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				IconSize size = (IconSize) evt.getNewValue();
				iconSizeActionsModel.setDefaultIconSize(size);
			}
		};
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
	public FileChooserPanel withProperties(FileChooserPanelProperties properties) {
		setPanelProperties(properties);
		return this;
	}

	@Override
	public FileChooserPanel createPanel() {
		setup();
		return this;
	}

	private void setup() {
		docking = dockingFactory.create(container);
		docking.setMainDockPanel(panel);
		docking.setPlacesDockPanel(placesPanel);
		docking.setInputDockPanel(inputPanel);
		directoryModel.setCurrentDirectory(currentDirectory);
		panel.setOptionsMenu(optionsMenu);
		panel.optionsButton.setAction(optionsAction);
		setupFileModel();
		setupNameField();
		setupNativateDirectories();
		setupFilesList();
		setupToolButtons();
		setupSorting();
		setupPlaces();
		setupTextPosition();
		setupLocation();
		setupIconSize();
	}

	private void setupLocation() {
		panel.locationField.setModel(locationsModel);
		panel.locationField.addActionListener(locationsListener);
		locationsModel.addLocation(currentDirectory);
		locationsModel.setSelectedItem(currentDirectory);
	}

	private void setupPlaces() {
		placesActionList = ActionList.decorate(placesPanel.placesList);
		placesActionList.addActionListener(placesListener);
		placesPanel.placesList.setModel(placesModel);
		placesPanel.placesList.setCellRenderer(placesRenderer);
	}

	private void setupSorting() {
		optionsMenu.sortDate.addActionListener(sortActionsModel
				.getSortDateAction());
		optionsMenu.sortName.addActionListener(sortActionsModel
				.getSortNameAction());
		optionsMenu.sortSize.addActionListener(sortActionsModel
				.getSortSizeAction());
		optionsMenu.sortType.addActionListener(sortActionsModel
				.getSortTypeAction());
		optionsMenu.sortDescending.addActionListener(sortActionsModel
				.getSortDescendingAction());
		optionsMenu.sortFolderFirst.addActionListener(sortActionsModel
				.getSortFolderFirstAction());
		sortActionsModel.setFileModel(fileModel);
		fileModel.addPropertyChangeListener(FILE_SORT_PROPERTY,
				fileSortListener);
		updateSelectedFileSort(properties.getFileSort());
		optionsMenu.sortDescending.setSelected(properties.isDescendingSort());
		optionsMenu.sortFolderFirst.setSelected(properties.isFolderFirstSort());
	}

	private void updateSelectedFileSort(FileSort sort) {
		UiOptionsMenu menu = optionsMenu;
		ButtonGroup group = menu.sortingGroup;
		switch (sort) {
		case DATE:
			group.setSelected(menu.sortDate.getModel(), true);
			break;
		case NAME:
			group.setSelected(menu.sortName.getModel(), true);
			break;
		case SIZE:
			group.setSelected(menu.sortSize.getModel(), true);
			break;
		case TYPE:
			group.setSelected(menu.sortType.getModel(), true);
			break;
		}
	}

	private void setupTextPosition() {
		TextPositionActionsModel model = textPositionActionsModel;
		optionsMenu.textOnly.addActionListener(model.getTextOnlyAction());
		optionsMenu.iconsOnly.addActionListener(model.getIconOnlyAction());
		optionsMenu.textAlongsideIcons.addActionListener(model
				.getTextAlongsideIconAction());
		properties.addPropertyChangeListener(TEXT_POSITION_PROPERTY,
				textPositionListener);
		textPositionListener.propertyChange(new PropertyChangeEvent(properties,
				TEXT_POSITION_PROPERTY, null, properties.getTextPosition()));
	}

	private void setupIconSize() {
		IconSizeActionsModel model = iconSizeActionsModel;
		optionsMenu.hugeIcon.addActionListener(model.getHugeSizeAction());
		optionsMenu.largeIcon.addActionListener(model.getLargeSizeAction());
		optionsMenu.mediumIcon.addActionListener(model.getMediumSizeAction());
		optionsMenu.smallIcon.addActionListener(model.getSmallSizeAction());
		optionsMenu.defaultIcon.addActionListener(model.getDefaultSizeAction());
		properties.addPropertyChangeListener(ICON_SIZE_PROPERTY,
				iconSizeListener);
		iconSizeListener.propertyChange(new PropertyChangeEvent(properties,
				ICON_SIZE_PROPERTY, null, properties.getIconSize()));
		properties.addPropertyChangeListener(DEFAULT_ICON_SIZE_PROPERTY,
				defaultIconSizeListener);
		defaultIconSizeListener.propertyChange(new PropertyChangeEvent(
				properties, DEFAULT_ICON_SIZE_PROPERTY, null, properties
						.getDefaultIconSize()));
	}

	private void setupFileModel() {
		fileModel.setFileSystemView(systemView);
		fileModel.setDirectory(currentDirectory);
		fileModel.addPropertyChangeListener(DIRECTORY_PROPERTY,
				directoryListener);
	}

	private void setupNameField() {
		properties.addPropertyChangeListener(SELECTED_FILES_IN_QUEUE_PROPERTY,
				selectedFilesInQueueListener);
		selectedFilesQueueModel.setSelectedFiles(properties
				.getSelectedFilesInQueue());
		inputPanel.nameField.setModel(selectedFilesQueueModel);
		inputPanel.nameField.setRenderer(fileNameRenderer);
		fileNameEditor.setEditorDelegate(inputPanel.nameField.getEditor());
		inputPanel.nameField.setEditor(fileNameEditor);
		fileNameEditor.setCurrentDirectory(currentDirectory);
		inputPanel.nameField.addActionListener(fileNameListener);
	}

	private void setupNativateDirectories() {
		navigateDirectories.setFileChooser(this);
		navigateDirectories.setFileModel(fileModel);
		navigateDirectories.setFileSystemView(systemView);
		navigateDirectories.setDirectoryModel(directoryModel);
		navigateDirectories.setList(panel.filesList);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setupFilesList() {
		FileViewRenderer renderer = views.get(properties.getView());
		JList list = panel.filesList;
		list.setModel(fileModel);
		list.setCellRenderer(renderer);
		list.setLayoutOrientation(renderer.getLayoutOrientation());
		list.setVisibleRowCount(renderer.getVisibleRowCount());
		list.setSelectionModel(selectionModel);
		list.putClientProperty("List.isFileList", Boolean.TRUE);
		selectionModel.setList(list);
		selectionModel.addListSelectionListener(fileSelectionListener);
	}

	private void setupToolButtons() {
		for (int i = 0; i < toolButtonsModel.getSize(); i++) {
			ToolAction action = toolButtonsModel.getActionAt(i);
			action.setFileModel(fileModel);
			action.setDirectoryModel(directoryModel);
			action.setFileSelectionModel(selectionModel);
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
	void setDockingFactory(DockingFactory dockingFactory) {
		this.dockingFactory = dockingFactory;
	}

	@Inject
	void setNavigateDirectories(NavigateDirectories navigateDirectories) {
		this.navigateDirectories = navigateDirectories;
	}

	@Inject
	void setPanelProperties(FileChooserPanelProperties properties) {
		this.properties = properties;
	}

	@Inject
	void setUiOptionsMenu(UiOptionsMenu optionsMenu) {
		this.optionsMenu = optionsMenu;
	}

	@Inject
	void setUiPanel(UiFilesListPanel panel) {
		this.panel = panel;
	}

	@Inject
	void setUiInputPanel(UiInputPanel panel) {
		this.inputPanel = panel;
	}

	@Inject
	void setUiPlacesPanel(UiPlacesPanel panel) {
		this.placesPanel = panel;
	}

	@Inject
	void setShortViewRenderer(DefaultShortViewRenderer renderer) {
		setFileViewRenderer(FileView.SHORT, renderer);
	}

	@Inject
	void setOptionsAction(OptionsAction action) {
		this.optionsAction = action;
	}

	@Inject
	void setSelectedFilesQueueModel(SelectedFilesQueueModel model) {
		this.selectedFilesQueueModel = model;
	}

	@Inject
	void setSortActionsModel(SortActionsModel model) {
		this.sortActionsModel = model;
	}

	@Override
	@Inject
	public void setTextPositionActionsModel(TextPositionActionsModel model) {
		this.textPositionActionsModel = model;
	}

	@Override
	public TextPositionActionsModel getTextPositionActionsModel() {
		return textPositionActionsModel;
	}

	@Override
	@Inject
	public void setIconSizeActionsModel(IconSizeActionsModel model) {
		this.iconSizeActionsModel = model;
	}

	@Override
	public IconSizeActionsModel getIconSizeActionsModel() {
		return iconSizeActionsModel;
	}

	@Override
	public FileChooserPanelProperties getFileChooserPanelProperties() {
		return properties;
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

	// @Inject TODO
	@Override
	public void setFilePropertiesModel(FilePropertiesModel model) {
		this.filePropertiesModel = model;
	}

	@Override
	public FilePropertiesModel getFilePropertiesModel() {
		return filePropertiesModel;
	}

	@Inject
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
	public void setPlacesRenderer(PlacesRenderer renderer) {
		this.placesRenderer = renderer;
	}

	@Override
	public PlacesRenderer getPlacesRenderer() {
		return placesRenderer;
	}

	@Inject
	@Override
	public void setToolButtonsModel(ToolButtonsModel model) {
		this.toolButtonsModel = model;
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

	@Inject
	@Override
	public void setFileNameRenderer(FileNameRenderer renderer) {
		this.fileNameRenderer = renderer;
	}

	@Inject
	@Override
	public void setFileNameEditor(FileNameEditor editor) {
		this.fileNameEditor = editor;
	}

	@Inject
	@Override
	public void setLocationsModel(LocationsModel model) {
		this.locationsModel = model;
	}

	@Override
	public LocationsModel getLocationsModel() {
		return locationsModel;
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

	@Override
	public <T extends JComponent> Map<String, T> getComponents(Pattern pattern,
			Class<? extends T>... componentClasses) {
		Map<String, T> map = new HashMap<String, T>(100);
		Map<String, T> components = getComponents(componentClasses);
		for (Map.Entry<String, T> entry : components.entrySet()) {
			Matcher matcher = pattern.matcher(entry.getKey());
			if (matcher.matches()) {
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends JComponent> Map<String, T> getComponents(
			Class<? extends T>... componentClasses) {
		Map<String, T> map = new HashMap<String, T>(100);
		for (Entry<String, JComponent> entry : getComponents().entrySet()) {
			Class<?> type = entry.getValue().getClass();
			for (Class<?> neededType : componentClasses) {
				if (type.equals(neededType)) {
					map.put(entry.getKey(), (T) entry.getValue());
				}
			}
		}
		return map;
	}

	@Override
	public Map<String, JComponent> getComponents() {
		Map<String, JComponent> map = new HashMap<String, JComponent>(100);
		map.putAll(panel.components);
		map.putAll(inputPanel.components);
		map.putAll(optionsMenu.components);
		return unmodifiableMap(map);
	}

}
