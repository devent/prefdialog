package com.anrisoftware.prefdialog.filechooser.panel.api;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface FileChooserPanelProperties {

	void setView(FileView view);

	FileView getView();

	void setMaxSelectedFilesQueue(int maxSelectedFiles);

	void setSelectedFiles(Set<File> selectedFiles);

	Set<File> getSelectedFiles();

	void addSelectedFiles(Collection<File> files);

	void clearSelectedFiles();

	int getMaxSelectedFilesQueue();

	void addSelectedFilesQueue(Set<File> selectedFiles);

	List<Set<File>> getSelectedFilesQueue();

}