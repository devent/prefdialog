package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.filechooser.FileSystemView;

public interface FileChooserPanel {

	static final String FILES_LIST_NAME = "files-list";

	static final String LOCATION_FIELD_NAME = "location-field";

	static final String PREVIEW_BUTTON_NAME = "preview-tool-button";

	static final String REFRESH_BUTTON_NAME = "refresh-tool-button";

	static final String UP_BUTTON_NAME = "up-tool-button";

	static final String SHOW_PREVIEW_BUTTON_NAME = "show-preview-tool-button";

	static final String FORWARD_BUTTON_NAME = "forward-tool-button";

	static final String BACK_BUTTON_NAME = "back-tool-button";

	static final String CANCEL_BUTTON_NAME = "cancel-button";

	static final String FILTER_FIELD_NAME = "filter-field";

	static final String APPROVE_BUTTON_NAME = "approve-button";

	static final String NAME_FIELD_NAME = "name-field";

	static final String PLACES_LIST_NAME = "places-list";

	static final String PLACES_TOOLBAR_NAME = "places-toolbar";

	static final String DETAILED_TREE_VIEW_MENU_NAME = "detailed-tree-view-menu";

	static final String TREE_VIEW_MENU_NAME = "tree-view-menu";

	static final String DETAILED_VIEW_MENU_NAME = "detailed-view-menu";

	static final String SHORT_VIEW_MENU_NAME = "short-view-menu";

	static final String SORT_FOLDER_FIRST_MENU_NAME = "sort-folder-first-menu";

	static final String SORT_DESCENDING_MENU_NAME = "sort-descending-menu";

	static final String SORT_TYPE_MENU_NAME = "sort-type-menu";

	static final String SORT_DATE_MENU_NAME = "sort-date-menu";

	static final String SORT_SIZE_MENU_NAME = "sort-size-menu";

	static final String SORT_NAME_MENU_NAME = "sort-name-menu";

	static final String SHOW_HIDDEN_FILES_MENU_NAME = "show-hidden-files-menu";

	static final String VIEW_MENU_NAME = "view-menu";

	static final String SORTING_MENU_NAME = "sorting-menu";

	static final String FILTER_LABEL_NAME = "filter-label";

	static final String NAME_LABEL_NAME = "name-label";

	static final String OPTIONS_BUTTON_NAME = "options-tool-button";

	static final String HUGE_ICON_MENU_NAME = "huge-icon-menu";

	static final String LARGE_ICON_MENU_NAME = "large-icon-menu";

	static final String MEDIUM_ICON_MENU_NAME = "medium-icon-menu";

	static final String SMALL_ICON_MENU_NAME = "small-icon-menu";

	static final String DEFAULT_ICON_MENU_NAME = "default-icon-menu";

	static final String ICON_SIZE_MENU_NAME = "icon-size-menu";

	static final String TEXT_ALONGSIDE_ICONS_MENU_NAME = "text-alongside-icons-menu";

	static final String TEXT_ONLY_MENU_NAME = "text-only-menu";

	static final String ICONS_ONLY_MENU_NAME = "icons-only-menu";

	static final String TEXT_POSITION_MENU_NAME = "text-position-menu";

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

	void setLocationsModel(LocationsModel model);

	LocationsModel getLocationsModel();

	@SuppressWarnings("rawtypes")
	void setFileViewRenderer(FileView view, FileViewRenderer renderer);

	void addActionListener(ActionListener l);

	void removeActionListener(ActionListener l);

	void updateUI();

	void setFileNameRenderer(FileNameRenderer renderer);

	void setFileNameEditor(FileNameEditor editor);

	void setPlacesRenderer(PlacesRenderer renderer);

	PlacesRenderer getPlacesRenderer();

	void setTextPositionActionsModel(TextPositionActionsModel model);

	TextPositionActionsModel getTextPositionActionsModel();

	void setIconSizeActionsModel(IconSizeActionsModel model);

	IconSizeActionsModel getIconSizeActionsModel();

	/**
	 * Returns the components in the file chooser panel for direct manipulation.
	 * 
	 * @return a {@link Map} where the keys are the names of the components and
	 *         the values are the {@link JComponent} components.
	 */
	Map<String, JComponent> getComponents();

	/**
	 * Returns the components in the file chooser panel for direct manipulation.
	 * 
	 * @param componentClasses
	 *            the needed {@link Class} types of the component.
	 * 
	 * @param <T>
	 *            the needed type of the component.
	 * 
	 * @return a {@link Map} where the keys are the names of the components and
	 *         the values are the {@link JComponent} components.
	 */
	<T extends JComponent> Map<String, T> getComponents(
			Class<? extends T>... componentClasses);

	/**
	 * Returns the components in the file chooser panel for direct manipulation.
	 * 
	 * @param pattern
	 *            the pattern that should match the name of the component.
	 * 
	 * @param componentClasses
	 *            the needed {@link Class} types of the component.
	 * 
	 * @param <T>
	 *            the needed type of the component.
	 * 
	 * @return a {@link Map} where the keys are the names of the components and
	 *         the values are the {@link JComponent} components.
	 */
	<T extends JComponent> Map<String, T> getComponents(Pattern pattern,
			Class<? extends T>... componentClasses);

}
