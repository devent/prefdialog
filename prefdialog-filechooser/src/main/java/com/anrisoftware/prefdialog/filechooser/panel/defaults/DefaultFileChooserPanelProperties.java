package com.anrisoftware.prefdialog.filechooser.panel.defaults;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.anrisoftware.globalpom.textposition.TextPosition;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelProperties;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileSort;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileView;
import com.anrisoftware.resources.images.api.IconSize;

/**
 * Saves the file chooser panel properties that the user can change.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class DefaultFileChooserPanelProperties implements
		FileChooserPanelProperties {

	private final PropertyChangeSupport support;

	private FileView view;

	private int maxSelectedFiles;

	private Set<File> selectedFiles;

	private final Deque<Set<File>> selectedFilesQueue;

	private FileSort sort;

	private boolean descending;

	private boolean folderFirst;

	private List<File> places;

	private TextPosition textPosition;

	private IconSize iconSize;

	private IconSize defaultIconSize;

	public DefaultFileChooserPanelProperties() {
		this.support = new PropertyChangeSupport(this);
		this.view = FileView.SHORT;
		this.sort = FileSort.NAME;
		this.maxSelectedFiles = 6;
		this.selectedFiles = new HashSet<File>();
		this.selectedFilesQueue = new ArrayDeque<Set<File>>();
		this.descending = false;
		this.folderFirst = true;
		this.places = new ArrayList<File>();
		this.textPosition = TextPosition.ICON_ONLY;
		this.iconSize = IconSize.SMALL;
		this.defaultIconSize = IconSize.SMALL;
	}

	@Override
	public void setView(FileView view) {
		this.view = view;
	}

	@Override
	public FileView getView() {
		return view;
	}

	@Override
	public void setFileSort(FileSort sort) {
		FileSort oldValue = this.sort;
		this.sort = sort;
		support.firePropertyChange(FILE_SORT_PROPERTY, oldValue, sort);
	}

	@Override
	public FileSort getFileSort() {
		return sort;
	}

	@Override
	public void setDescendingSort(boolean descending) {
		this.descending = descending;
	}

	@Override
	public boolean isDescendingSort() {
		return descending;
	}

	@Override
	public void setFolderFirstSort(boolean folderFirst) {
		this.folderFirst = folderFirst;
	}

	@Override
	public boolean isFolderFirstSort() {
		return folderFirst;
	}

	@Override
	public void setSelectedFiles(Set<File> selectedFiles) {
		Set<File> oldValue = this.selectedFiles;
		this.selectedFiles = selectedFiles;
		support.firePropertyChange(SELECTED_FILES_PROPERTY, oldValue,
				selectedFiles);
	}

	@Override
	public void addSelectedFiles(Collection<File> files) {
		if (selectedFiles.addAll(files)) {
			support.firePropertyChange(SELECTED_FILES_PROPERTY, null,
					selectedFiles);
		}
	}

	@Override
	public void clearSelectedFiles() {
		selectedFiles.clear();
		support.firePropertyChange(SELECTED_FILES_PROPERTY, null, selectedFiles);
	}

	@Override
	public Set<File> getSelectedFiles() {
		return selectedFiles;
	}

	@Override
	public void setMaxSelectedFilesInQueue(int max) {
		int oldValue = this.maxSelectedFiles;
		this.maxSelectedFiles = max;
		support.firePropertyChange(MAX_SELECTED_FILES_IN_QUEUE_PROPERTY,
				oldValue, max);
	}

	@Override
	public int getMaxSelectedFilesInQueue() {
		return maxSelectedFiles;
	}

	@Override
	public void addSelectedFilesToQueue(Set<File> files) {
		if (this.selectedFilesQueue.size() > maxSelectedFiles) {
			this.selectedFilesQueue.removeLast();
		}
		this.selectedFilesQueue.push(files);
		support.fireIndexedPropertyChange(SELECTED_FILES_IN_QUEUE_PROPERTY,
				selectedFilesQueue.size(), null, selectedFilesQueue);
	}

	@Override
	public List<Set<File>> getSelectedFilesInQueue() {
		return new ArrayList<Set<File>>(selectedFilesQueue);
	}

	public void setPlaces(List<File> places) {
		Set<File> unique = new HashSet<File>(places);
		this.places = new ArrayList<File>(unique);
		support.firePropertyChange(PLACES_PROPERTY, null, this.places);
	}

	public List<File> getPlaces() {
		return places;
	}

	public void addPlace(File place) {
		if (!places.contains(place)) {
			if (places.add(place)) {
				support.firePropertyChange(PLACES_PROPERTY, null, this.places);
			}
		}
	}

	public void removePlace(File place) {
		if (places.remove(place)) {
			support.firePropertyChange(PLACES_PROPERTY, null, this.places);
		}
	}

	@Override
	public void setTextPosition(TextPosition position) {
		TextPosition oldValue = this.textPosition;
		this.textPosition = position;
		support.firePropertyChange(TEXT_POSITION_PROPERTY, oldValue, position);
	}

	@Override
	public TextPosition getTextPosition() {
		return textPosition;
	}

	@Override
	public void setIconSize(IconSize iconSize) {
		IconSize oldValue = this.iconSize;
		this.iconSize = iconSize;
		support.firePropertyChange(ICON_SIZE_PROPERTY, oldValue, iconSize);
	}

	@Override
	public IconSize getIconSize() {
		return iconSize;
	}

	@Override
	public void setDefaultIconSize(IconSize defaultIconSize) {
		IconSize oldValue = this.defaultIconSize;
		this.defaultIconSize = defaultIconSize;
		support.firePropertyChange(DEFAULT_ICON_SIZE_PROPERTY, oldValue,
				iconSize);
	}

	@Override
	public IconSize getDefaultIconSize() {
		return defaultIconSize;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener l) {
		support.addPropertyChangeListener(l);
	}

	@Override
	public void addPropertyChangeListener(String name, PropertyChangeListener l) {
		support.addPropertyChangeListener(name, l);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener l) {
		support.removePropertyChangeListener(l);
	}

	@Override
	public void removePropertyChangeListener(String name,
			PropertyChangeListener l) {
		support.removePropertyChangeListener(name, l);
	}
}
