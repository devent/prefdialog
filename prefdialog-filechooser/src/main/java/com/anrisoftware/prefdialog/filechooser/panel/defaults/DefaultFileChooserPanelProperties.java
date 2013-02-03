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

import com.anrisoftware.prefdialog.filechooser.panel.api.FileChooserPanelProperties;
import com.anrisoftware.prefdialog.filechooser.panel.api.FileView;

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

	public DefaultFileChooserPanelProperties() {
		this.support = new PropertyChangeSupport(this);
		this.view = FileView.SHORT;
		this.maxSelectedFiles = 6;
		this.selectedFiles = new HashSet<File>();
		this.selectedFilesQueue = new ArrayDeque<Set<File>>();
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
