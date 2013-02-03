package com.anrisoftware.prefdialog.filechooser.panel.defaults;

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

	private FileView view;

	private int maxSelectedFiles;

	private Set<File> selectedFiles;

	private final Deque<Set<File>> selectedFilesQueue;

	public DefaultFileChooserPanelProperties() {
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
		this.selectedFiles = selectedFiles;
	}

	@Override
	public void addSelectedFiles(Collection<File> files) {
		selectedFiles.addAll(files);
	}

	@Override
	public void clearSelectedFiles() {
		selectedFiles.clear();
	}

	@Override
	public Set<File> getSelectedFiles() {
		return selectedFiles;
	}

	@Override
	public void setMaxSelectedFilesQueue(int max) {
		this.maxSelectedFiles = max;
	}

	@Override
	public int getMaxSelectedFilesQueue() {
		return maxSelectedFiles;
	}

	@Override
	public void addSelectedFilesQueue(Set<File> selectedFiles) {
		if (this.selectedFilesQueue.size() > maxSelectedFiles) {
			this.selectedFilesQueue.removeLast();
		}
		this.selectedFilesQueue.push(selectedFiles);
	}

	@Override
	public List<Set<File>> getSelectedFilesQueue() {
		return new ArrayList<Set<File>>(selectedFilesQueue);
	}

}
